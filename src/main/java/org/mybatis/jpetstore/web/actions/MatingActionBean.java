package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import org.mybatis.jpetstore.domain.Mating;
import org.mybatis.jpetstore.service.MatingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@SessionScope
public class MatingActionBean extends AbstractActionBean{

    private static final String NEW_MATING = "/WEB-INF/jsp/mating/WriteMatingForm.jsp";
    private static final String LIST_MATING = "/WEB-INF/jsp/mating/ListMating.jsp";
    private static final String VIEW_MATING = "/WEB-INF/jsp/mating/ViewMating.jsp";
    private static final String CATEGORY_MATING = "/WEB-INF/jsp/mating/CategoryMating.jsp";

    @SpringBean
    private transient MatingService matingService;

    private Mating mating = new Mating();
    private int matingId;
    private String title;
    private String type;
    private String username;
    private String species;
    private String sex;
    private int age;
    private String content;
    private List<Mating> matingList;
    private FileBean newAttachment;

    public Mating getMating() {
        return mating;
    }

    public void setMating(Mating mating) {
        this.mating = mating;
    }

    public int getMatingId() {
        return matingId;
    }

    public String getTitle() {
        return mating.getTitle();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMatingId(int matingId) {
        this.matingId = matingId;
    }

    public String getType(String type) {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return mating.getSpecies();
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return mating.getSex();
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return mating.getAge();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContent() {
        return mating.getContent();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Mating> getMatingList() {
        return matingList;
    }

    public void setMatingList(List<Mating> matingList) {
        this.matingList = matingList;
    }

    public FileBean getNewAttachment() {
        return newAttachment;
    }

    public void setNewAttachment(FileBean newAttachment) {
        this.newAttachment = newAttachment;
    }

    public String getUsername() {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        return accountBean.getUsername();
    }

    public void setUsername() { mating.setUsername(getUsername()); }

    /**
     * Category board.
     *
     * @return the resolution
     */
    public Resolution categoryMating() {
        return new ForwardResolution(CATEGORY_MATING);
    }


    /**
     * List board.
     *
     * @return the resolution
     */


    public ForwardResolution listMating() {
        if(type != null){
            matingList = matingService.getMatingList(type);
        }
        return new ForwardResolution(LIST_MATING);
    }

    /**
     * View board.
     *
     * @return the resolution
     */

    public Resolution viewMating() {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

        clear();

        if (accountBean == null ||!accountBean.isAuthenticated()) {
            setMessage("Login First!");
            return new ForwardResolution(AccountActionBean.class);
        } else {
            Integer val = matingId;
            System.out.println(matingId);
            if (val != null) {
                mating = matingService.getMating(matingId);
            }
            return new ForwardResolution(VIEW_MATING);
        }
    }

    /**
     * New write form.
     *
     * @return the resolution
     */

    public Resolution writeMatingForm() {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

        clear();
        if (accountBean == null ||!accountBean.isAuthenticated()) {
            setMessage("Login First!");
            return new ForwardResolution(AccountActionBean.class);
        } else {
            return new ForwardResolution(NEW_MATING);
        }
    }
    /**
     * New board.
     *
     * @return the resolution
     */
    public Resolution newMating() {
        setUsername();
        if(mating.getTitle()==null||mating.getSpecies()==null
                ||mating.getContent()==null){
            setMessage("Please post and try checking your text.");
            return new ForwardResolution(NEW_MATING);
        }
        matingService.insertMating(mating);
        matingList = matingService.getMatingList(type);
        return new ForwardResolution(LIST_MATING);
    }

    public void clear() {
        mating = new Mating();
        matingList = null;
    }

}
