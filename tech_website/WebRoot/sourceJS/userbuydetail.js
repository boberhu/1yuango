$(document).ready(function () {
	var resultCount = $("#resultCount").val();
    var pageNo = 0;
    //分页事件
    $("#pagination").pagination(resultCount, {
    	current_page:pageNo,
        prev_text: "上一页",
        next_text: "下一页",
        num_display_entries: 5,
        num_edge_entries:1,
        link_to:"",
        prev_show_always:false,
        next_show_always:false,
        items_per_page:50,
        callback: pageselectCallback
    });
    
    function pageselectCallback(pageNo) {
    	var id = $("#historyId").val();
    	var url ="/user/getRandomNumberList.action?id="+id+"&pageNo="+pageNo;
    	$.ajax({
    		url:url,
    		type:"get",
    		data:"String",
    		beforeSend:changeLoading,
    		success:function(data){
    			$("#numberText").empty();
    			$(".pageUL").show();
           		$(".pageUL").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(resultCount/50)+"</i>&nbsp;&nbsp;共<i>"+resultCount+"</i>条记录</li>");
    			var html = "";
    			for (var d = 0; d < data.length; d++) {
					html += data[d] +" ";
				}
			 	$("#changeLoading").hide();
				$("#numberText").html(html);
    		}
    	});
    }
    
    function changeLoading(){
   		$("#changeLoading").show();
   	}

});