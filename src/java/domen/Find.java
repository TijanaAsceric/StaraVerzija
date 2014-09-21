/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author asceric
 */
public class Find {

    private String nameOfCompany;
    private String nameOfEmployee;
    private String jobOfEmployee;
    private String country;
    private int page;

    public Find() {
    }

    public Find(String nameOfCompany, String nameOfEmployee, String jobOfEmployee, String country, int page) {
        this.nameOfCompany = nameOfCompany;
        this.nameOfEmployee = nameOfEmployee;
        this.jobOfEmployee = jobOfEmployee;
        this.country = country;
        this.page = page;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getNameOfEmployee() {
        return nameOfEmployee;
    }

    public void setNameOfEmployee(String nameOfEmployee) {
        this.nameOfEmployee = nameOfEmployee;
    }

    public String getJobOfEmployee() {
        return jobOfEmployee;
    }

    public void setJobOfEmployee(String jobOfEmployee) {
        this.jobOfEmployee = jobOfEmployee;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
