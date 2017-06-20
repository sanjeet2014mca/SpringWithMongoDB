<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Sanju | WB</title>
    
     <link href="${pageContext.request.contextPath}/static/basecss/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/plugins/datepicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/custome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/slider/bootstrap-slider.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/basecss/plugins/dataTables/datatables.min.css" rel="stylesheet">
    <script type="text/javascript">
    function getBlockChainBlocksonload(){
    	window.location.reload();
    }
      function getBlockChainBlocks(){
 
    	  $.ajax({
    		    type : "GET",
    		    url : "${pageContext.request.contextPath}/block/getInitialBlocks",
    		    success: function(result){
    		    	var data = JSON.parse(result);
    		    	if (data.errorCode == 0) {
    		    		 document.getElementById('blockinfo').innerHTML= "";
		    			 document.getElementById('blockinfo').innerHTML += "<table id='blocks' class='table table-striped table-bordered table-hover dataTables-example'><thead><tr id='tdheaderIdsent'><th>Block Number</th><th>State Hash</th><th>Transactions</th></tr></thead><tbody id='blocksDetails'></tbody></table>";
    		    		 var blocks = data.resultObject;
    		    		  for (var i = 0; i < blocks.length ; i++) {
    		    			  var block = blocks[i];
    		    			if(block.blockId != 0){
    		    			 document.getElementById('blocksDetails').innerHTML+="<tr id='block"+block.blockId+"'></tr>";
    		    			 document.getElementById("block"+block.blockId).innerHTML+="<td>"+block.blockId+"</td>";
    		    			 document.getElementById("block"+block.blockId).innerHTML+="<td><a href='#' data-toggle='tooltip' class='' data-placement='bottom' title='State Hash :"+block.stateHash+" Previous Hash :"+block.previousBlockHash+"'>"+block.stateHash.substring(0,6)+"</span></td>";
    		    			 document.getElementById("block"+block.blockId).innerHTML+="<td id='transactions"+block.blockId+"'></td>";
    		    			  var transactions = block.transactions;
    		    			  if(transactions.length>0){
    		    				  for (var j = 0; j < transactions.length; j++) {
    		    					     var transaction = transactions[j];
    	    		    				 document.getElementById("transactions"+block.blockId).innerHTML+="<a href='#' data-toggle='modal' onclick='modelPopupTransactionDetails("+JSON.stringify(transaction)+");'><span>"+transaction.txid+"</span></a> || <span> Type: "+transaction.type+"</span> || <span> Function: "+transaction.function+"</span> || <span> Arguments: "+transaction.arguments+"</span><br> ";
    	    		    			 } 
    		    			  }
    		    			}
    		    		 } 
    		    		  $('.dataTables-example').DataTable({
    		    			  "order": [[ 0, "desc" ]]
    		    		  });
    		    	} else{
    		    		alert(data.errorDetails);
    		    	}
    		    }
    		}); 
    	  
      }
      
      function modelPopupTransactionDetails(transaction){
      	document.getElementById("modelBodyDiv").innerHTML = "";
    	document.getElementById("modelBodyDiv").innerHTML+= "<h3><b>Transaction Details<b></h3><hr>"; 
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Transaction Id : </b>"+transaction.txid+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Transaction Type : </b>"+transaction.type+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Chaincode ID : </b>"+transaction.chainCodeName+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Function Name : </b>"+transaction.function+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Arguments : </b>"+transaction.arguments+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Metadata : </b>"+transaction.metaData+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Signature : </b>"+transaction.signature+"</span><br>";
    	document.getElementById("modelBodyDiv").innerHTML +="<span><b> Nonce : </b>"+transaction.nonce+"</span><br><hr>";
    	
    	  	
    	$('#modalpopuptransactiondetail').modal('show');
    } 
      
      function validatefield(event){
    			 var iKeyCode = (event.which) ? event.which : event.keyCode
    				        if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57)){
    				            return false;
    				        }else{
    				          return true;
    				        }
       }
      
        function getNewBlocks(){
    		     $.ajax({
        		    type : "POST",
        		    url : "${pageContext.request.contextPath}/block/getNewBlocks",
        		    success: function(result){
        		    	console.log(result);
        		    	var data = JSON.parse(result);
        		    	  if(data.errorCode == 0) {
        		    		 var blocks = data.resultObject;
         		    		  for (var i = blocks.length; i--;) {
         		    			  var block = blocks[i];
         		    			 var table = document.getElementById("blocksDetails");
         		    		     var row = table.insertRow(0);
         		    		     var cell1 = row.insertCell(0);
         		    		     var cell2 = row.insertCell(1);
         		    		     var cell3 = row.insertCell(2);
         		    		     cell1.innerHTML = block.blockId;
         		    		     cell2.innerHTML = "<td><a href='#' data-toggle='tooltip' data-placement='bottom' title='State Hash :"+block.stateHash+" Previous Hash :"+block.previousBlockHash+"'>"+block.stateHash.substring(0,6)+"</span></td>";
         		    			  var transactions = block.transactions;
         		    			  if(transactions.length>0){
         		    				  for (var j = 0; j < transactions.length; j++) {
         		    					     var transaction = transactions[j];
         		    					    cell3.innerHTML = "<a href='#' data-toggle='modal' onclick='modelPopupTransactionDetails("+JSON.stringify(transaction)+");'><span>"+transaction.txid+"</span></a> || <span> Type: "+transaction.type+"</span> || <span> Function: "+transaction.function+"</span> || <span> Arguments: "+transaction.arguments+"</span><br> ";
         	    		    			 } 
         		    			  }
         		    		 }
         		    		var table = document.getElementById("blocksDetails");
         		    		var x = table.rows.length;
         				    if(x > 100){
         				    	for (var k = x-1; k >99 ; k--) {
         				    		table.deleteRow(k);
         				    	}
         				    }
        		    	 } 
        		    }
        		});   
      } 
       
     setInterval(getNewBlocks, 30000); 
       
     function getTransactionDetail() {
    	   $.ajax({
   		    type : "POST",
   		    url : "${pageContext.request.contextPath}/block/getTransactionDetails",
   		    data : {
   		       "txid" : $('#txid').val().replace(/\s/g, "") 
   		    },
   		    success: function(result){
   		    	var data = JSON.parse(result);
   		    	if (data.errorCode == 0) {
   		    		var transaction = data.resultObject;
   		    		document.getElementById('transactiondetailspanel').innerHTML= "";
   		    		document.getElementById('transactiondetailspanel').innerHTML+="<div class='col-sm-12'><table class='table table-bordered table-hover width: 100%;border:2px'><tr><td rowspan='9' style='width: 12%;'><br/><br/><br/><br/><br/><br/><br/><br/><br/><b><span id='transID'></span></b></td></tr><tr><th><b>Transaction Type</b></th><td><span id='transType'></span></td></tr><tr><th><b>ChainCode ID</b></th><td><span id='chaincodeID'></span></td></tr><tr><th><b>Function</b></th><td><span id='functionName'></span></td></tr></span></td></tr><tr><th><b>Arguments</b></th><td><span id='arguments'></span></td></tr><tr><th><b>MetaData</b></th><td><span id='metaData'></span></td></tr><tr><th><b>Signature</b></th><td><span id='signature'></span></td></tr><tr><th><b>Nonce</b></th><td><span id='nonce'></span></td></tr></table></div>";	
   		    		$("#transID").text(transaction.txid);
   		    		$("#transType").text(transaction.type);
   		    		$("#chaincodeID").text(transaction.chainCodeName);
   		    		$("#functionName").text(transaction.function);
   		    		$("#arguments").text(transaction.arguments);
   		    		$("#metaData").text(transaction.metaData);
   		    		$("#signature").text(transaction.signature);
   		    		$("#nonce").text(transaction.nonce);
   		    		$("#blockID").hide();
   		    		$("#blockinfo").hide();
   		    		$("#txnID").show();
   		    	    txid.value=''; 
   		    	} else{
   		    		alert(data.errorDetails);
   		    		txid.value='';
   		    	}
   		    }
   		}); 
    	}
       
       function getBlockDetail() {
    		if($('#blocknum').val() >0){
    	   $.ajax({
   		    type : "POST",
   		    url : "${pageContext.request.contextPath}/block/getBlockData",
   		    data : {
   		       "blocknum" : $('#blocknum').val()
   		    },
   		    success: function(result){
   		    	var data = JSON.parse(result);
   		    	if (data.errorCode == 0) {
   		    		var block = data.resultObject;
   		    		document.getElementById('blockdetailsPanel').innerHTML= "";
   		    		document.getElementById('blockdetailsPanel').innerHTML+="<div class='col-sm-2'><table style='width: 100%'><tr><td style='text-align: center;'><br/><b>Block Number</b></td></tr><tr><td style = 'text-align: center;'><br/><h1><b><span id='blockno'></span></b></h1></td></tr></table></div><div class='col-sm-10'><table class='table table-bordered table-hover width: 100%;border:2px'><tr><th><b>State Hash</b></th><td><span id='statehash'></span></td></tr><tr><th><b>Previous Hash</b></th><td><span id='previousHash'></span></td></tr></table></div>";	
   		    		$("#blockno").text(block.blockId);
   		    		$("#statehash").text(block.stateHash);
   		    		$("#previousHash").text(block.previousBlockHash);
   		    		document.getElementById('transactiondetailspanel').innerHTML= "";
   		    		document.getElementById('transactiondetailspanel').innerHTML += "<table id='txnDatatable' class='table table-striped table-bordered table-hover dataTables-example'><thead><tr id='tdheaderIdsent'><th>Transaction ID</th><th>Transaction Type</th><th>Chaincode ID</th> <th>Function Name</th><th>Arguments</th><th>Metadata</th><th>Signature</th><th>Nonce</th></tr></thead><tbody id='txndetails'></tbody></table>";
   			      	var transactions = block.transactions;
   		    	    for (var j = 0; j < transactions.length; j++) {
   		        	var transaction = transactions[j];
   		    		document.getElementById("txndetails").innerHTML+="<tr><td>"+transaction.txid+"</td><td>"+transaction.type+"</td><td>"+transaction.chainCodeName+"</td><td>"+transaction.function+"</td><td>"+transaction.arguments+"</td><td>"+transaction.metaData+"</td><td>"+transaction.signature+"</td><td>"+transaction.nonce+"</td></tr>";
   		    	    }
   		    	 	$(".panel-default").show();
   		    		$("#blockinfo").hide();
   		        	blocknum.value='';
	   		  $('.dataTables-example').DataTable();
	   		  } else{
   		    		alert(data.errorDetails);
   		    		blocknum.value='';
   		    	}
   		    }
   		}); 
    	}else{
    			alert("Blockheight should not be 0");
    		}
	}
    </script>
</head>

<%-- <jsp:include page="/WEB-INF/jsp/blockchainexplorer/blockchainexplorerMenu.jsp"></jsp:include>
 --%><body>
    <div id="wrapper">
        <div id="page-wrapper" class="gray-bg">
<%--          <jsp:include page="/WEB-INF/jsp/blockchainexplorer/blockchainexplorerheader.jsp"></jsp:include> 
 --%>            <div class="row wrapper page-heading">
               
                	<div class="ibox-content">
									<div class="row">
												
								<div class="panel panel-default" id="blockID" style="display: none;">
									<div class="panel-heading"
										style="color: #fff; background-color: #515072;">
										<i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp; <b>BlockDetails</b>
									</div>
									<div class="panel-body" id="blockdetailsPanel">
										</div>
										
										</div>
										
										<div class="panel panel-default" id="txnID" style="display: none;">
									<div class="panel-heading"
										style="color: #fff; background-color: #515072;">
										<i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp; <b>Transaction details</b>
									</div>
									<div class="panel-body" id="transactiondetailspanel">
										</div>
										</div>
										<!-- <button type="button" class="btn btn-default"
														data-dismiss="modal" onclick="getBlockChainBlocks();">Close</button> -->
									</div>
									<div class="panel panel-default" id="blockinfo" style="border: 0;">
                
                </div>
								</div>
                 
                  <div class="modal fade" id="modalpopuptransactiondetail" role="dialog">
					<div class="modal-dialog">
						<span class="button b-close" data-dismiss="modal"><span>X</span></span>
						<div class="modal-content">
							<div class="modal-body form-horizontal" id="modelBodyDiv" style="word-break: break-all;">
							
							</div>
						</div>
					</div>
				</div>
                
              
            </div>
			<%--  <jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include> 
 --%>
        </div>
        </div>
        
   <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/static/basejs/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/basejs/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/basejs/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath}/static/basejs/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    
     <!-- Data Table javascript -->
    <script src="${pageContext.request.contextPath}/static/basejs/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="${pageContext.request.contextPath}/static/basejs/plugins/dataTables/datatables.min.js"></script>


    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath}/static/basejs/kc.js"></script>
    <script src="${pageContext.request.contextPath}/static/basejs/custome.js"></script>
    <script src="${pageContext.request.contextPath}/static/basejs/plugins/pace/pace.min.js"></script>
	
    <script src="${pageContext.request.contextPath}/static/basejs/slider/bootstrap-slider.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/static/basejs/plugins/iCheck/icheck.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/basejs/plugins/datepicker/bootstrap-datepicker.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function () {
	    	txid.value='';
	    	blocknum.value='';
	    	$('[data-toggle="tooltip"]').tooltip();
		   	 $('.dataTables-example').DataTable();
		   	  getBlockChainBlocks(); 
		 });
   
    </script>    
</body>

</html> 