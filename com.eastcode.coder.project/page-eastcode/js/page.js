function toPageFirstIndex(formId){
	if(document.getElementById('view.pageIndex').value!=1) {
    	document.getElementById('view.pageIndex').value=1;
    	document.getElementById(formId).submit();
	}
}
function toPagePreIndex(formId){
	if(document.getElementById('view.pageIndex').value!=1) {
    	document.getElementById('view.pageIndex').value=parseInt(document.getElementById('view.pageIndex').value)-1;
    	document.getElementById(formId).submit();
	}
}
function toPageNextIndex(formId){  
	if(document.getElementById('view.pageIndex').value!=document.getElementById('view.pageCount').value) {
    	document.getElementById('view.pageIndex').value=parseInt(document.getElementById('view.pageIndex').value)+1;
    	document.getElementById(formId).submit();
   	}
}
function toPageLastIndex(formId){ 
	if(document.getElementById('view.pageIndex').value!=document.getElementById('view.pageCount').value) {
    	document.getElementById('view.pageIndex').value=document.getElementById('view.pageCount').value;
    	document.getElementById(formId).submit();
	}
}
function toPageInputIndex(formId){
    document.getElementById(formId).submit();
}
function resetPageFirstIndex(formId){
    document.getElementById('view.pageIndex').value=1;
    document.getElementById(formId).submit();
}
function checkedAll(event) {
	var obj = event.srcElement?event.srcElement:event.target;
	var nodeList = document.getElementsByName("checked");
	for(var i=0; i < nodeList.length;i++) {
		nodeList[i].checked=obj.checked;
	}
}

function dataListOnMouseOver(event) {
	var obj = event.srcElement?event.srcElement:event.target;
	if(obj.tagName=="INPUT") {
		obj=obj.parentNode;
	}
	obj.parentNode.style.cursor="pointer";
	obj.parentNode.style.backgroundColor='#F0E68C';
}

function dataListOnMouseOut(event) {
	var obj = event.srcElement?event.srcElement:event.target;
	if(obj.tagName=="INPUT") {
		obj=obj.parentNode;
	}
	obj.parentNode.style.backgroundColor='#e5f1f4';
	obj.parentNode.style.cursor="default";
}

function clickCheckedData(event) {
	var obj = event.srcElement?event.srcElement:event.target;
	
	if(obj.tagName=="INPUT") {
		return false;
	}
	obj = obj.parentNode.firstChild;
	while (!obj.tagName) {
		obj = obj.nextSibling;
	}
	obj=obj.firstChild;
	while (!obj.tagName) {
		obj = obj.nextSibling;
	}
	obj.checked=!obj.checked;
}

function dbClickCheckedData(event) {
	var obj = event.srcElement?event.srcElement:event.target;

	if(obj.tagName=="INPUT") {
		return false;
	}
	obj = obj.parentNode.firstChild;
	while (!obj.tagName) {
		obj = obj.nextSibling;
	}
	obj=obj.firstChild;
	while (!obj.tagName) {
		obj = obj.nextSibling;
	}
	obj.checked=true;
}
function getSelections(checkedName,idName,ids){
	var checkedNum = 0;
	var selecteds = document.getElementsByName(checkedName);
	var id = document.getElementsByName(idName);
	ids.value="";
	for(var i=0; i < selecteds.length;i++) {
		if(selecteds[i].checked==true) {
			checkedNum+=1;
			if(ids.value=="") {
				ids.value=id[i].value;
			} else {
				ids.value+=","+id[i].value;
			}
		}
	}
	return checkedNum;
}
function getSelectionsAndNames(checkedName,idName,nameList,ids){
	var checkedNum = 0;
	var selecteds = document.getElementsByName(checkedName);
	var id = document.getElementsByName(idName);
	var name = document.getElementsByName(nameList);
	ids.value="";
	for(var i=0; i < selecteds.length;i++) {
		if(selecteds[i].checked==true) {
			checkedNum+=1;
			if(ids.value=="") {
				ids.value=id[i].value+","+name[i].value;
			} else {
				ids.value+=","+id[i].value+","+name[i].value;
			}
		}
	}
	return checkedNum;
}

var  highlightcolor='#eafcd5';
var  clickcolor='#51b2f6';
function  changeto(event){
	var source=event.srcElement?event.srcElement:event.target;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	return;
	
	while(source.parentNode!=undefined&&source.tagName!="TD")
	source=source.parentNode;
	if(source.parentNode!=undefined) {
		source=source.parentNode;
	}
	if(source!=undefined) {
		cs  =  source.children;
		if  (cs.length>0&&cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor) {
			for(i=0;i<cs.length;i++){
				cs[i].style.backgroundColor=highlightcolor;
			}
		}
	}
}

function  changeback(event){
	var source=event.srcElement?event.srcElement:event.target;
	
	var fromElement = event.fromElement?event.fromElement:event.target;
	var toElement = event.toElement?event.toElement:event.relatedTarget;
	if  (fromElement==toElement||source==toElement||source.id=="nc")
	return
	while(source.parentNode!=undefined&&source.tagName!="TD")
	source=source.parentNode;
	
	if(source.parentNode!=undefined) {
		source=source.parentNode;
	}
	if(source.parentNode!=undefined) {
		var cs  =  source.children;
		if  (toElement!=source&&cs.length>0&&cs[1].style.backgroundColor!=clickcolor)
			for(i=0;i<cs.length;i++){
				cs[i].style.backgroundColor="";
			}
	}
}

function  clickto(){
	var source=event.srcElement?event.srcElement:event.target;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	return;
	while(source.tagName!="TD")
	source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor=clickcolor;
	} else { 
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
}
String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	}