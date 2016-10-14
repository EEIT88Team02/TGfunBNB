package model.Member;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import model.Article.ArticleBean;
import model.Report.ReportBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "Member")
public class MemberBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberID;// 流水號ID.
	private String account;// 會員帳號.
	private byte[] pwd;// 會員密碼
	private String name;// 姓名.
	private String sex;// 性別.
	private java.util.Date birthday;// 生日.
	private String ID;// 身分證.
	private String email;// 電子信箱.
	private String Celphone;// 行動電話.
	private String Telephone;// 室內電話.
	private String address;// 聯絡地址.
	private byte[] photo;// 會員照片
	private int vip;// VIP等級
	private int Bonus;// 紅利(可消費)
	private int TotalBonus;// 累積紅利(固定)
	private boolean ssl;// 是否為SSL認證ssl
	private int MemberStatus;

	@OneToMany(	mappedBy = "members" ,
				cascade = { CascadeType.REMOVE })
	private Set<ArticleBean> messages;

	public Set<ArticleBean> getMessages() {
		return messages;
	}

	public void setMessages(Set<ArticleBean> messages) {
		this.messages = messages;
	}

	@OneToMany(	mappedBy = "members" ,
				cascade = { CascadeType.REMOVE })
	private Set<ReportBean> reports;

	public Set<ReportBean> getReports() {
		return reports;
	}

	public void setReports(Set<ReportBean> reports) {
		this.reports = reports;
	}

	

	@Override
	public String toString() {
		return "MemberBean [memberID=" + memberID + ", account=" + account + ", pwd=" + Arrays.toString(pwd) + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", ID=" + ID + ", email="
				+ email + ", Celphone=" + Celphone + ", Telephone=" + Telephone + ", address=" + address + ", photo=" + Arrays.toString(photo) + ", vip=" + vip + ", Bonus=" + Bonus + ", TotalBonus="
				+ TotalBonus  +"]";
	}

	public static void main(String[] args) {
		try {

			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			// 新增
			// MemberBean insert = new MemberBean();
			// insert.setAccount("VVV");
			// insert.setPwd("!!!456".getBytes());
			// insert.setName("SSS");
			// insert.setID("p789456123");
			// session.save(insert);
			// System.out.println("insert=" + insert);
			//// 查詢
//			 MemberBean select = (MemberBean) session.get(MemberBean.class,1);
//			 System.out.println("select="+select.getReports());
			//// 修改
			// MemberBean update =(MemberBean) session.get(MemberBean.class,6);
			// update.setAccount("EEE");
			// update.setID("R789456897");
			// update.setBirthday(new java.util.Date());
			// session.update(update);
			// System.out.println("update="+update);
			//// 刪除
			// session.delete(select);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	
	public int getMemberID() {
		return memberID;
	}

	
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	
	public String getAccount() {
		return account;
	}

	
	public void setAccount(String account) {
		this.account = account;
	}

	
	public byte[] getPwd() {
		return pwd;
	}

	
	public void setPwd(byte[] pwd) {
		this.pwd = pwd;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getSex() {
		return sex;
	}

	
	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public java.util.Date getBirthday() {
		return birthday;
	}

	
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	
	public String getID() {
		return ID;
	}

	
	public void setID(String iD) {
		ID = iD;
	}

	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getCelphone() {
		return Celphone;
	}

	
	public void setCelphone(String celphone) {
		Celphone = celphone;
	}

	
	public String getTelephone() {
		return Telephone;
	}

	
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	
	public String getAddress() {
		return address;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	
	public byte[] getPhoto() {
		return photo;
	}

	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	public int getVip() {
		return vip;
	}

	
	public void setVip(int vip) {
		this.vip = vip;
	}

	
	public int getBonus() {
		return Bonus;
	}

	
	public void setBonus(int bonus) {
		Bonus = bonus;
	}

	
	public int getTotalBonus() {
		return TotalBonus;
	}

	
	public void setTotalBonus(int totalBonus) {
		TotalBonus = totalBonus;
	}

	
	public boolean isSsl() {
		return ssl;
	}

	
	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	
	public int getMemberStatus() {
		return MemberStatus;
	}

	
	public void setMemberStatus(int memberStatus) {
		MemberStatus = memberStatus;
	}

	

}