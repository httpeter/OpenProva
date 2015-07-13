package org.op.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Labels implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String mailMSGNewMemberSubscriptionSubject;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityAdditionalMessage;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String menuItemAdmin;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String project;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String defaultEmailWindsCoordinator;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String menuItemExistingProject;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsTableInstrument;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String MSGTimeoutBody;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsTableFirstname;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String projectFrom;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityCommentsByContact;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String organizationName;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String menuItemHome;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String mailMSGToAdminNewMemberSubscriptionSubject;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContacts;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String defaultEmailWindsCoordinatorPWD;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminUsername;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityLocation;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityEndTime;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String projectUntil;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String menuItemProjects;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic(fetch=FetchType.LAZY)
    private String sixteenCharsEncryptionSalt;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityDescription;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsTableLastname;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String projectSelect;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsAddContact;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=2048,scale=0,precision=0)
    @Basic
    private String projectSubscriptionNewMSG;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String save;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=2048,scale=0,precision=0)
    @Basic
    private String mailMSGToAdminNewMemberSubscriptionBody;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityStartTime;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String appTitle;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsTablePhone;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String MSGTimeoutHeader;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsTableNotes;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityDate;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=2048,scale=0,precision=0)
    @Basic
    private String mailMSGNewMemberSubscriptionBody;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminPassword;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String defaultEmailStringsCoordinator;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityTime;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String projectSubscriptionPanelHeader;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String defaultEmailStringsCoordinatorPWD;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=512,scale=0,precision=0)
    @Basic
    private String projectRepertoire;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String activityPresent;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String menuItemNewProject;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String menuItemLogout;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic(fetch=FetchType.LAZY)
    private String sixteenCharsEncryptionPassword;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String adminTabContactsTableEmail;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=2048,scale=0,precision=0)
    @Basic
    private String welcomeMSG;

    public Labels() {

    }
   
    public String getMailMSGNewMemberSubscriptionSubject() {
        return this.mailMSGNewMemberSubscriptionSubject;
    }

    public void setMailMSGNewMemberSubscriptionSubject(String mailMSGNewMemberSubscriptionSubject) {
        this.mailMSGNewMemberSubscriptionSubject = mailMSGNewMemberSubscriptionSubject;
    }
   
    public String getActivityAdditionalMessage() {
        return this.activityAdditionalMessage;
    }

    public void setActivityAdditionalMessage(String activityAdditionalMessage) {
        this.activityAdditionalMessage = activityAdditionalMessage;
    }
   
    public String getMenuItemAdmin() {
        return this.menuItemAdmin;
    }

    public void setMenuItemAdmin(String menuItemAdmin) {
        this.menuItemAdmin = menuItemAdmin;
    }
   
    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }
   
    public String getDefaultEmailWindsCoordinator() {
        return this.defaultEmailWindsCoordinator;
    }

    public void setDefaultEmailWindsCoordinator(String defaultEmailWindsCoordinator) {
        this.defaultEmailWindsCoordinator = defaultEmailWindsCoordinator;
    }
   
    public String getMenuItemExistingProject() {
        return this.menuItemExistingProject;
    }

    public void setMenuItemExistingProject(String menuItemExistingProject) {
        this.menuItemExistingProject = menuItemExistingProject;
    }
   
    public String getAdminTabContactsTableInstrument() {
        return this.adminTabContactsTableInstrument;
    }

    public void setAdminTabContactsTableInstrument(String adminTabContactsTableInstrument) {
        this.adminTabContactsTableInstrument = adminTabContactsTableInstrument;
    }
   
    public String getMSGTimeoutBody() {
        return this.MSGTimeoutBody;
    }

    public void setMSGTimeoutBody(String MSGTimeoutBody) {
        this.MSGTimeoutBody = MSGTimeoutBody;
    }
   
    public String getAdminTabContactsTableFirstname() {
        return this.adminTabContactsTableFirstname;
    }

    public void setAdminTabContactsTableFirstname(String adminTabContactsTableFirstname) {
        this.adminTabContactsTableFirstname = adminTabContactsTableFirstname;
    }
   
    public String getProjectFrom() {
        return this.projectFrom;
    }

    public void setProjectFrom(String projectFrom) {
        this.projectFrom = projectFrom;
    }
   
    public String getActivityCommentsByContact() {
        return this.activityCommentsByContact;
    }

    public void setActivityCommentsByContact(String activityCommentsByContact) {
        this.activityCommentsByContact = activityCommentsByContact;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getOrganizationName() {
        return this.organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
   
    public String getMenuItemHome() {
        return this.menuItemHome;
    }

    public void setMenuItemHome(String menuItemHome) {
        this.menuItemHome = menuItemHome;
    }
   
    public String getMailMSGToAdminNewMemberSubscriptionSubject() {
        return this.mailMSGToAdminNewMemberSubscriptionSubject;
    }

    public void setMailMSGToAdminNewMemberSubscriptionSubject(String mailMSGToAdminNewMemberSubscriptionSubject) {
        this.mailMSGToAdminNewMemberSubscriptionSubject = mailMSGToAdminNewMemberSubscriptionSubject;
    }
   
    public String getAdminTabContacts() {
        return this.adminTabContacts;
    }

    public void setAdminTabContacts(String adminTabContacts) {
        this.adminTabContacts = adminTabContacts;
    }
   
    public String getDefaultEmailWindsCoordinatorPWD() {
        return this.defaultEmailWindsCoordinatorPWD;
    }

    public void setDefaultEmailWindsCoordinatorPWD(String defaultEmailWindsCoordinatorPWD) {
        this.defaultEmailWindsCoordinatorPWD = defaultEmailWindsCoordinatorPWD;
    }
   
    public String getAdminUsername() {
        return this.adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
   
    public String getActivityLocation() {
        return this.activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }
   
    public String getActivityEndTime() {
        return this.activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }
   
    public String getProjectUntil() {
        return this.projectUntil;
    }

    public void setProjectUntil(String projectUntil) {
        this.projectUntil = projectUntil;
    }
   
    public String getMenuItemProjects() {
        return this.menuItemProjects;
    }

    public void setMenuItemProjects(String menuItemProjects) {
        this.menuItemProjects = menuItemProjects;
    }
   
    public String getSixteenCharsEncryptionSalt() {
        return this.sixteenCharsEncryptionSalt;
    }

    public void setSixteenCharsEncryptionSalt(String sixteenCharsEncryptionSalt) {
        this.sixteenCharsEncryptionSalt = sixteenCharsEncryptionSalt;
    }
   
    public String getActivityDescription() {
        return this.activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
   
    public String getAdminTabContactsTableLastname() {
        return this.adminTabContactsTableLastname;
    }

    public void setAdminTabContactsTableLastname(String adminTabContactsTableLastname) {
        this.adminTabContactsTableLastname = adminTabContactsTableLastname;
    }
   
    public String getProjectSelect() {
        return this.projectSelect;
    }

    public void setProjectSelect(String projectSelect) {
        this.projectSelect = projectSelect;
    }
   
    public String getAdminTabContactsAddContact() {
        return this.adminTabContactsAddContact;
    }

    public void setAdminTabContactsAddContact(String adminTabContactsAddContact) {
        this.adminTabContactsAddContact = adminTabContactsAddContact;
    }
   
    public String getProjectSubscriptionNewMSG() {
        return this.projectSubscriptionNewMSG;
    }

    public void setProjectSubscriptionNewMSG(String projectSubscriptionNewMSG) {
        this.projectSubscriptionNewMSG = projectSubscriptionNewMSG;
    }
   
    public String getSave() {
        return this.save;
    }

    public void setSave(String save) {
        this.save = save;
    }
   
    public String getMailMSGToAdminNewMemberSubscriptionBody() {
        return this.mailMSGToAdminNewMemberSubscriptionBody;
    }

    public void setMailMSGToAdminNewMemberSubscriptionBody(String mailMSGToAdminNewMemberSubscriptionBody) {
        this.mailMSGToAdminNewMemberSubscriptionBody = mailMSGToAdminNewMemberSubscriptionBody;
    }
   
    public String getActivityStartTime() {
        return this.activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }
   
    public String getAppTitle() {
        return this.appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }
   
    public String getAdminTabContactsTablePhone() {
        return this.adminTabContactsTablePhone;
    }

    public void setAdminTabContactsTablePhone(String adminTabContactsTablePhone) {
        this.adminTabContactsTablePhone = adminTabContactsTablePhone;
    }
   
    public String getMSGTimeoutHeader() {
        return this.MSGTimeoutHeader;
    }

    public void setMSGTimeoutHeader(String MSGTimeoutHeader) {
        this.MSGTimeoutHeader = MSGTimeoutHeader;
    }
   
    public String getAdminTabContactsTableNotes() {
        return this.adminTabContactsTableNotes;
    }

    public void setAdminTabContactsTableNotes(String adminTabContactsTableNotes) {
        this.adminTabContactsTableNotes = adminTabContactsTableNotes;
    }
   
    public String getActivityDate() {
        return this.activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }
   
    public String getMailMSGNewMemberSubscriptionBody() {
        return this.mailMSGNewMemberSubscriptionBody;
    }

    public void setMailMSGNewMemberSubscriptionBody(String mailMSGNewMemberSubscriptionBody) {
        this.mailMSGNewMemberSubscriptionBody = mailMSGNewMemberSubscriptionBody;
    }
   
    public String getAdminPassword() {
        return this.adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
   
    public String getDefaultEmailStringsCoordinator() {
        return this.defaultEmailStringsCoordinator;
    }

    public void setDefaultEmailStringsCoordinator(String defaultEmailStringsCoordinator) {
        this.defaultEmailStringsCoordinator = defaultEmailStringsCoordinator;
    }
   
    public String getActivityTime() {
        return this.activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }
   
    public String getProjectSubscriptionPanelHeader() {
        return this.projectSubscriptionPanelHeader;
    }

    public void setProjectSubscriptionPanelHeader(String projectSubscriptionPanelHeader) {
        this.projectSubscriptionPanelHeader = projectSubscriptionPanelHeader;
    }
   
    public String getDefaultEmailStringsCoordinatorPWD() {
        return this.defaultEmailStringsCoordinatorPWD;
    }

    public void setDefaultEmailStringsCoordinatorPWD(String defaultEmailStringsCoordinatorPWD) {
        this.defaultEmailStringsCoordinatorPWD = defaultEmailStringsCoordinatorPWD;
    }
   
    public String getProjectRepertoire() {
        return this.projectRepertoire;
    }

    public void setProjectRepertoire(String projectRepertoire) {
        this.projectRepertoire = projectRepertoire;
    }
   
    public String getActivityPresent() {
        return this.activityPresent;
    }

    public void setActivityPresent(String activityPresent) {
        this.activityPresent = activityPresent;
    }
   
    public String getMenuItemNewProject() {
        return this.menuItemNewProject;
    }

    public void setMenuItemNewProject(String menuItemNewProject) {
        this.menuItemNewProject = menuItemNewProject;
    }
   
    public String getMenuItemLogout() {
        return this.menuItemLogout;
    }

    public void setMenuItemLogout(String menuItemLogout) {
        this.menuItemLogout = menuItemLogout;
    }
   
    public String getSixteenCharsEncryptionPassword() {
        return this.sixteenCharsEncryptionPassword;
    }

    public void setSixteenCharsEncryptionPassword(String sixteenCharsEncryptionPassword) {
        this.sixteenCharsEncryptionPassword = sixteenCharsEncryptionPassword;
    }
   
    public String getAdminTabContactsTableEmail() {
        return this.adminTabContactsTableEmail;
    }

    public void setAdminTabContactsTableEmail(String adminTabContactsTableEmail) {
        this.adminTabContactsTableEmail = adminTabContactsTableEmail;
    }
   
    public String getWelcomeMSG() {
        return this.welcomeMSG;
    }

    public void setWelcomeMSG(String welcomeMSG) {
        this.welcomeMSG = welcomeMSG;
    }
}
