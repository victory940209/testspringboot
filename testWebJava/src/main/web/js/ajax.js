/**
 * ajax
 * @param actionUrl     : URL
 * @param dataType      : json
 * @param actionType    : POST
 * @param actionParam
 * @param actionCallBack
 */
function ajaxAction(actionUrl,dataType, actionType, actionParam,loadingYn, actionCallBack){

	if(loadingYn)loadingOn();

	$.ajax({
		type     : actionType,
		url      : actionUrl,
		async    : false,
		dataType : dataType,
		data     : actionParam,
		success  : function(data, status) {
			if(loadingYn)loadingOff();
			if(actionCallBack != null) actionCallBack(data);
		},
		error    : function(request, status, error) {
			if(loadingYn)loadingOff();
			commMsg.alert("안내","오류가 발생하였습니다. \n다시 시도해 주세요.");
		},
		complete : function() {

		}
	});
}

function asicAjaxAction(actionUrl,dataType, actionType, actionParam,loadingYn, actionCallBack){

	if(loadingYn)loadingOn();

	$.ajax({
		type     : actionType,
		url      : actionUrl,
		async    : true,
		dataType : dataType,
		data     : actionParam,
		success  : function(data, status) {
			if(loadingYn)loadingOff();
			if(actionCallBack != null) actionCallBack(data);
		},
		error    : function(request, status, error) {
			if(loadingYn)loadingOff();
			commMsg.alert("안내","오류가 발생하였습니다. \n다시 시도해 주세요.");
		},
		complete : function() {

		}
	});
}

/**
 * grid ajax
 * @param actionUrl   : URL
 * @param actionParam : param
 * @param tmpl        : html
 * @param loadingYn   : 로딩바 true,false
 * @param gridId      : gridId
 * @param pagingYn      : 페이징여부,true,false
 * @param actionCallBack      : 처리 함수
 */
function ajaxGrid(actionUrl, actionParam,tmpl,loadingYn,gridId,pagingYn,actionCallBack,colcnt){

	try {
		var pYn = false;
		if(pagingYn != false){
			pYn = true;
		}
		if(pYn){
			if($(event.target).attr('id') &&
				($(event.target).attr('id').indexOf('search')!=-1 || $(event.target).attr('id').indexOf('Search')!=-1)) {
				$("#pageIndex").val(1);
				if(actionParam && typeof actionParam==='object') {
					if(Array.isArray(actionParam)){
						actionParam.push({name: "search", value: "1"});
					}else{ actionParam.search = 1;}
				}else{ actionParam+= '&search=1'; }
			}
		}
	} catch(e) {}
	if(loadingYn)loadingOn();

	$.ajax({
		type     : "POST",
		url      : actionUrl,
		async    : false,
		dataType : "json",
		data     : actionParam,
		success  : function(result, status) {

//			console.log(result);
//			console.log(gridId);
			if(loadingYn)loadingOff();
          	var $ui = $("#"+gridId);

          	$ui.empty();

        	var data = result.list;
        	var ddd = 201901;
        	//console.log(result.list[0].NAME);

        	//console.log();
        	if(data.length>0){
	 			$.each(data, function(idx) {
					$.template("listTmpl",tmpl);
				 	$ui.append($.tmpl("listTmpl",this).data('data',this));
				 	//console.log(data[idx]);
				});
	 			var $tot = $("#"+gridId+"Tot");
				if(pYn){
					$tot.html(format.FUND(result.searchMap.TOT_CNT));
					ajaxPaging(result.searchMap.TOT_CNT,gridId);
				}else{
					$tot.html(data.length);
				}
			 	var $b = $("#"+gridId+" tr");
			 	var rowColor = "#ffff99";
				$b.click(function () {
					$b.each(function(index) {$(this).css('background', 'white').removeClass("on");});
					if(this.style.background == "" || this.style.background =="white")$(this).css('background', rowColor).addClass("on");
			    });
				$b.mouseover(function() {$(this).css('background', rowColor);});
				$b.mouseout(function() {if(!$(this).hasClass("on"))$(this).css('background', 'white');});

				if (actionCallBack  != null){
					actionCallBack(result);
				}
        	}else{
        		var $tot = $("#"+gridId+"Tot");
        		$tot.html("0");
        		var a = 0;
        		if (colcnt != null){
        			 a = colcnt;
        		} else {
        			a = $ui.parents().html().match(/<th /g).length;
        		}
        		var emt = "<tr><td colspan="+a+" class='empty'>검색 내용이 없습니다.</td></tr>";
				$.template("listTmpl",emt);
			 	$ui.append($.tmpl("listTmpl",""));
        		if(pYn)$("#"+gridId+"AjaxPaging").html("");
        	}
		},
		error    : function(request, status, error) {
			if(loadingYn)loadingOff();
			commMsg.alert("안내","오류가 발생하였습니다. \n다시 시도해 주세요.");
		},
		complete : function() {

		}
	});
}

/**
 * ajaxPaging
 * @param TOT_CNT : 총갯수
 */
function ajaxPaging(TOT_CNT,gridId){
	var param = {
		pageIndex :$("#pageIndex").val(),
		listCnt :$("#listCnt").val(),
		TOT_CNT : TOT_CNT
	};
	//console.log(param);
	ajaxAction("/fsys/common/ajaxPaging.do", "","POST", param, false, function actionCallBack(data){
		$("#"+gridId+"AjaxPaging").html(data);
	});
}

//jquery ajax 처리 POST방식만 처리
function jqAjax(url,params, loadingYN, fn) {
	var callUrl = url;

	//if(loadingYN == 'Y'){loadingOn();}

	$.ajax({
	   		type: "POST",
		    dataType: "json",
		    url: callUrl,
		    data: params,
		    cache: false,
		    //contentType:'application/x-www-form-urlencoded; charset=EUC-KR',
		    success: function(data) {
		    	fn(data);
		    },
		    complete: function(data) {
		    	if(loadingYN == 'Y'){loadingOff();}
		    	if(data.RESULT_CD == '8'){
		    		loginPopUp();
		    	}
		    },
		    error: function(xhr, textStatus, thrownError) {
		    	if(loadingYN == 'Y'){loadingOff();}
		    }
	});
}

function jqAjaxAsync(url,params, loadingYN, fn) {
	var callUrl = url;

	//if(loadingYN == 'Y'){loadingOn();}

	$.ajax({
	   		type: "POST",
		    dataType: "json",
		    async: true,
		    url: callUrl,
		    data: params,
		    cache: false,
		    //contentType:'application/x-www-form-urlencoded; charset=EUC-KR',
		    success: function(data) {
		    	if(data.RESULT_CD == '8'){
		    		loginPopUp();
		    	}
		    	fn(data);
		    },
		    complete: function(data) {
		    	if(data.RESULT_CD == '8'){
		    		loginPopUp();
		    	}
		    	if(loadingYN == 'Y'){loadingOff();}
		    },
		    error: function(xhr, textStatus, thrownError) {
		    	if(loadingYN == 'Y'){loadingOff();}
		    }
	});
}





