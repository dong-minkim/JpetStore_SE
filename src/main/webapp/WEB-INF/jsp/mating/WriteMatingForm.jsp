<%@ include file="../common/IncludeTop.jsp"%>

<div id="Board"><stripes:form
        beanclass="org.mybatis.jpetstore.web.actions.MatingActionBean"
        focus="">

    <h3>Write Mating Form</h3>

    <table>
        <tr>
            <td>writer</td>
            <td>${actionBean.username}</td>
        </tr>
        <tr>
            <td>title</td>
            <td><stripes:text name="mating.title" />
        </tr>

        <tr>
            <td>type</td>
            <td>
                <stripes:select name="mating.type">
                <option value="DOGS" selected>dog</option>
                <option value="CATS" >cat</option>
                <option value="BIRDS">bird</option>
                <option value="REPTILES">reptile</option>
                <option value="FISH">fish</option>
            </stripes:select>
            </td>
        </tr>

        <tr>
            <td>species</td>
            <td><stripes:text name="mating.species" /></td>
        </tr>

        <tr>
            <td>sex</td>
            <td><stripes:select name="mating.sex">
                <option value="MALE" selected>male</option>
                <option value="FEMALE" >female</option>
            </stripes:select>
            </td>
        </tr>

        <tr>
            <td>age</td>
            <td><stripes:text name="mating.age" /></td>
        </tr>

        <tr>
            <td>content</td>
            <td>
                <stripes:textarea rows="15" cols="65" name="mating.content" />
            </td>
        </tr>
    </table>

    <stripes:submit name="newMating" value="submit" />

</stripes:form></div>

<%@ include file="../common/IncludeBottom.jsp"%>