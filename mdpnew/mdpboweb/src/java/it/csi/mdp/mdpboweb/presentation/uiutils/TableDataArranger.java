/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.uiutils;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TableDataArranger {

	@SuppressWarnings("unchecked")
	public TableArrangedData arrange(List listaOggetti,
			TableStatus tableStatus, List<TableFilter> filter) {

		String callback = tableStatus.getCallback();
		if (callback == null) {
			callback = "";
		}
		// start=25&limit=25&sort=replycount&dir=ASC&_dc=1239896937456&callback=stcCallback1009
		String start = tableStatus.getStart();
		if (start == null) {
			start = "0";
		}

		String limit = tableStatus.getLimit();
		if (limit == null) {
			limit = "5";
		}

		String valueSelector = tableStatus.getValueSelector();
		if (valueSelector == null) {
			valueSelector = "id";
		}

		String metodo = tableStatus.getSort();

		String dir = tableStatus.getDir();

		boolean sortAsc = true;
		if (dir != null && dir.equalsIgnoreCase("DESC")) {
			sortAsc = false;
		}

		List<Object> listaValori = new ArrayList<Object>();

		if (filter != null && filter.size() > 0) {
			for (Object oggetto : listaOggetti) {
				boolean flagString = true;
				boolean flagList = true;
				boolean flagNumero = true;
				boolean isGtLt = false;
				boolean flagGtLt = false;
				boolean isDateComparison = false;
				boolean flagDateComparison = false;
				boolean flagData = true;
				boolean flagBoolean = true;
				for (TableFilter filtro : filter) {

					DataFilter dataFilter = filtro.getData();
					String campo = filtro.getField();

					Method method = null;

					try {
						method = Utility.convertiMetodo(campo, oggetto);
					} catch (SecurityException e1) {
						throw new TableDataArrangerException(
								"Errore su griglia filtrata", e1.getCause());
					}
					/*filtro su campo di testo*/
					if (dataFilter.getType().equalsIgnoreCase("string")) {
						try {
							String vv = (String) method.invoke(oggetto,
									(Object[]) null);
							flagString = false;
							if (vv.toLowerCase().indexOf(
									dataFilter.getValue().toLowerCase()) != -1) {
								flagString = true;
							}
						} catch (Exception e) {
							throw new TableDataArrangerException(
									"Filtro campo testo non eseguito",
									e.getCause());
						}

					}
					/*filtro su campo con lista di valori*/
					if (dataFilter.getType().equalsIgnoreCase("list")) {
						try {
							String vv = (String) method.invoke(oggetto,
									(Object[]) null);
							flagList = false;
							StringTokenizer tok = new StringTokenizer(
									dataFilter.getValue(), ",");
							String tmp = "";
							while (tok.hasMoreTokens()) {
								tmp = tok.nextToken().trim();
								if (tmp.equalsIgnoreCase(vv)) {
									flagList = true;
									break;
								}
							}
						} catch (Exception e) {
							throw new TableDataArrangerException(
									"Filtro lista valori check non eseguito",
									e.getCause());
						}
					}
					/*filtro su campo numerico*/
					if (dataFilter.getType().equalsIgnoreCase("numeric")) {
						try {
							String vv = String.valueOf(method.invoke(oggetto,
									(Object[]) null));
							Double v = Double.parseDouble(vv);
							flagNumero = false;
							Double numeroPassato = Double
									.parseDouble(dataFilter.getValue());
							if (dataFilter.getComparison().equalsIgnoreCase(
									"eq")
									&& v.compareTo(numeroPassato) == 0) {
								flagNumero = true;
							}
							if (dataFilter.getComparison().equalsIgnoreCase(
									"lt")) {
								isGtLt = true;
							}
							if (dataFilter.getComparison().equalsIgnoreCase(
									"lt")
									&& v.compareTo(numeroPassato) < 0) {
								flagNumero = true;
								flagGtLt = true;
							}
							if (dataFilter.getComparison().equalsIgnoreCase(
									"gt")
									&& v.compareTo(numeroPassato) > 0
									&& (!isGtLt || (isGtLt && flagGtLt))) {
								flagNumero = true;
							}
						} catch (Exception e) {
							throw new TableDataArrangerException(
									"Filtro campo numerico non eseguito",
									e.getCause());
						}
					}
					/*filtro su campo data*/
					if (dataFilter.getType().equalsIgnoreCase("date")) {
						try {
							String vv = (String) method.invoke(oggetto,
									(Object[]) null);
							flagData = false;
							Date dataOrigine = Utility.convertToDate(vv);
							Date dataPassata = Utility.convertToDate(dataFilter
									.getValue());
							if (dataFilter.getComparison().equalsIgnoreCase(
									"eq")
									&& dataOrigine.equals(dataPassata)) {
								flagData = true;
							}
							if (dataFilter.getComparison().equalsIgnoreCase(
									"lt")) {
								isDateComparison = true;
							}

							if (dataFilter.getComparison().equalsIgnoreCase(
									"lt")
									&& dataOrigine.before(dataPassata)) {
								flagData = true;
								flagDateComparison = true;
							}
							if (dataFilter.getComparison().equalsIgnoreCase(
									"gt")
									&& dataOrigine.after(dataPassata)
									&& (!isDateComparison || (isDateComparison && flagDateComparison))) {
								flagData = true;
							}

						} catch (Exception e) {
							throw new TableDataArrangerException(
									"Filtro campo data non eseguito",
									e.getCause());
						}
					}
					/*filtro su campo boolean*/
					if (dataFilter.getType().equalsIgnoreCase("boolean")) {
						try {
							flagBoolean = false;
							boolean vv = (Boolean) method.invoke(oggetto,
									(Object[]) null);
							boolean valore = Boolean.parseBoolean(dataFilter
									.getValue());
							if (vv == valore) {
								flagBoolean = true;
							}
						} catch (Exception e) {
							throw new TableDataArrangerException(
									"Filtro campo boolean non eseguito",
									e.getCause());
						}
					}

				}

				if (flagString && flagList && flagNumero && flagData
						&& flagBoolean) {
					listaValori.add(oggetto);
				}
			}
		} else {
			listaValori.addAll(listaOggetti);
		}

		/**ordino eventualmente la lista*/
		if (metodo != null) {
			DynamicComparator.sort(listaValori, metodo, sortAsc);
		}
		int i = Integer.parseInt(start);
		int j = Integer.parseInt(limit);
		int k = 0;

		List<Object> l1 = new ArrayList<Object>();

		//***se la tabella non e' paginata allora aggiungo tutto
		if (j == 0 || j == -1) {
			l1.addAll(listaValori);
		} else {
			//***metto gli oggetti relativi al range di paginazione
			for (Object oggetto : listaValori) {
				k++;
				if (k >= i && k <= i + j) {

					l1.add(oggetto);
				}
			}
		}

		TableArrangedData griglia = new TableArrangedData();
		griglia.setNumtot(listaValori.size());
		griglia.setElenco(l1);
		griglia.setPositions(injectPosictions(l1, listaOggetti, valueSelector));
		if (tableStatus.isClearStatus()) {
			griglia.setClearStatus(true);
		}

		return griglia;

	}

	/**
	 * passa un vettore contenente l'id dell'oggetto
	 * e la sua posizione originale nella lista
	 * @param l1
	 * @param listaOggetti
	 * @param valueSelector
	 * @return
	 */
	private List<DataPosition> injectPosictions(List<Object> l1,
			List<Object> listaOggetti, String valueSelector) {
		List<DataPosition> poss = new ArrayList<DataPosition>();

		for (Object oggetto : l1) {
			try {
				Method method = Utility.convertiMetodo(valueSelector, oggetto);
				String id = String.valueOf(method.invoke(oggetto,
						(Object[]) null));
				DataPosition dataPos = new DataPosition();
				dataPos.setId(id);
				dataPos.setPosition(getElementPosition(id, listaOggetti,
						valueSelector));
				poss.add(dataPos);
			} catch (SecurityException e1) {
				throw new TableDataArrangerException(
						"Errore su metodo injectPosictions", e1.getCause());
			} catch (IllegalArgumentException e2) {
				throw new TableDataArrangerException(
						"Errore su metodo injectPosictions", e2.getCause());
			} catch (IllegalAccessException e3) {
				throw new TableDataArrangerException(
						"Errore su metodo injectPosictions", e3.getCause());
			} catch (InvocationTargetException e4) {
				throw new TableDataArrangerException(
						"Errore su metodo injectPosictions", e4.getCause());
			}
		}
		return poss;
	}

	private int getElementPosition(String id, List<Object> listaOggetti,
			String valueSelector) {
		int pos = 0;

		for (Object oggetto : listaOggetti) {

			pos++;
			Method method = Utility.convertiMetodo(valueSelector, oggetto);
			String idS;
			try {
				idS = String.valueOf(method.invoke(oggetto, (Object[]) null));
			} catch (IllegalArgumentException e) {
				throw new TableDataArrangerException(
						"Errore su metodo getElementPosition", e.getCause());
			} catch (IllegalAccessException e) {
				throw new TableDataArrangerException(
						"Errore su metodo getElementPosition", e.getCause());
			} catch (InvocationTargetException e) {
				throw new TableDataArrangerException(
						"Errore su metodo getElementPosition", e.getCause());
			}

			if (id.equalsIgnoreCase(idS)) {
				return pos;

			}
		}
		return pos;
	}
}
