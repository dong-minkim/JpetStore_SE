<%@ include file="../common/IncludeTop.jsp"%>

<div id="Board" style="">
    <h3>Board</h3>

    <table >
        <tr>
            <td >writer</td>
            <td>${actionBean.board.username}</td>

        </tr>
        <tr>
            <td >title</td>
            <td width="500px" style="word-break:break-all">${actionBean.board.title}</td>
        </tr>
        <tr>
            <td >content</td>
            <td width="500px" style="word-break:break-all">${actionBean.board.content}</td>
        </tr>
    </table>

    <stripes:link class="Button"
                  beanclass="org.mybatis.jpetstore.web.actions.BoardActionBean"
                  event="listBoard">
        List
    </stripes:link>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>