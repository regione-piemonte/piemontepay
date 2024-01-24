<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/tag_lib.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<h1>Logout</h1>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<form class="form-horizontal">
				<div role="alert" class="alert alert-info alert-dismissible">Sei sicuro di voler uscire?</div>
				<div class="form-group pulsantiera">
					<div class="col-sm-12">
						<button type="submit" class="btn btn-default">No</button>
						<button type="submit" class="btn btn-primary pull-right">S&igrave;</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
