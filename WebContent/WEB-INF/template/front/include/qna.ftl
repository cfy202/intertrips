
<#assign ctx = request.contextPath />
<#list qList as question>
<div class="qa_list_q_item" >
	<div class="qa_list_q_info">
		<font class="uname0" >${(question.name)!}</font> ${(question.createDate)?string("yyyy-MM-dd HH:mm:ss")}
	    <a href="javascript:showMoreIframe('reply','${(question.id)!}');" ><img src="${ctx!}/assets-web/images/btn-reply.gif">回复</a>
	</div>
	<div class="qa_list_q_content">${(question.content)!}</div>
	<div class="qa_list_a">
		<div class="qa_list_a_title">回复 (${(question.answerCount)!})</div>
		<#if question.answerCount?? && question.answerCount != 0>
			<#list question.qnaAnswerList as answer>
	    <div class="qa_list_a_item" >
	    	<div class="qa_list_a_info">
	        	<font class="uname0" >${(answer.name)!}</font> ${(answer.createDate)?string("yyyy-MM-dd HH:mm:ss")}
	        </div>
	        <div class="clear"></div> 
	        <div class="qa_list_a_content">${(answer.content)!}</div>
	    </div>
	    	</#list>
	   </#if>
	</div>
</div>
</#list>