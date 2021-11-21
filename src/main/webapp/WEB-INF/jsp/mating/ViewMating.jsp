<%@ include file="../common/IncludeTop.jsp"%>

<div id="Mating" style="">
    <h3>${actionBean.mating.type} Mating</h3>

    <table>
        <tr>
            <td>writer</td>
            <td>${actionBean.mating.username}</td>
        </tr>
        <tr>
            <td>title</td>
            <td width="500px" style="word-break:break-all">${actionBean.mating.title}</td>
        </tr>
        <tr>
            <td>species</td>
            <td width="500px" style="word-break:break-all">
                ${actionBean.mating.species}</td>
        </tr>
        <tr>
            <td>sex</td>
            <td width="500px" style="word-break:break-all">
                ${actionBean.mating.sex}</td>
        </tr>

        <tr>
            <td>age</td>
            <td width="500px" style="word-break:break-all">
                ${actionBean.mating.age}</td>
        </tr>

        <tr>
            <td>content</td>
            <td width="500px" style="word-break:break-all">${actionBean.mating.content}</td>
        </tr>
    </table>

    <stripes:link class="Button"
                  beanclass="org.mybatis.jpetstore.web.actions.MatingActionBean"
                  event="listMating">
        List
    </stripes:link>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>