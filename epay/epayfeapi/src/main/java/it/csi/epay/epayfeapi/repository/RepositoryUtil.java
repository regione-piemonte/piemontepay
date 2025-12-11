package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;


public final class RepositoryUtil {

	// creato per evitare duplicazione
	@SuppressWarnings ( { "rawtypes", "unchecked" } )
	public static void applyPagination ( PagedListResultDTO result, PanacheQuery panacheQuery, int pageIndex, int pageSize ) {
		result.setList ( panacheQuery.page ( pageIndex, pageSize ).list () );
		result.setCurrentPage ( pageIndex + 1 );
		result.setPageSize ( pageSize );
		result.setTotalPages ( panacheQuery.pageCount () );
		result.setTotalElements ( panacheQuery.count () );
	}
}
