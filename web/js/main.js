/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var rootURL = "http://localhost:8080/ProjekatISTijana/webresources/MyPath/";
var page = 0;



function getData() {
    getCountries();
    getJobs();   
}

function getCountries() {
    console.log('getcon');
setTimeout(function() {
    $.ajax({
        type: 'GET',
        url: rootURL + 'countries',
        dataType: "json", 
        success: renderCOList
        
    });
    
},2000);
     
}

function renderCOList(data) {

    var list = data === null ? [] : (data instanceof Array ? data : [data]);
    
 console.log('listazem');
   $.each(list, function(key, value) {   
     $('#selectCO')
         .append($("<option></option>")
         .attr("value",key)
         .text(value.location)); 
 
});
}


function getJobs() {
   console.log('getjobs');
setTimeout(function() {
    $.ajax({
        type: 'GET',
        url: rootURL + 'employees',
        dataType: "json", 
        success: renderJobsList     
        });
       },5000);
         
        }

function renderJobsList(data) {

    var list = data === null ? [] : (data instanceof Array ? data : [data]);
    console.log('kod job liste');
    

  
    
    $.each(list, function(key, value) {   
     $('#selectJob')
         .append($("<option></option>")
         .attr("value",key)
         .text(value.jobTitle)); 
 
 
});
}




function search(page1) {
    page = page1;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL + 'findOrg',
        dataType: "json",
        data: formToJSON(page1),
        success: showOrganizations,
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Greska: ' + textStatus + "\r\n Nije uspelo dovlačenje podataka sa servera. Proverite da li je server pokrenut.");
        }
    });
    return false;
}

function formToJSON(page1) {
    console.log(page1);
    var co = $('#selectCO option:selected').text();
    if (co == 'all')
        co = "";
    var job = $('#selectJob option:selected').text();
    if (job == 'all')
        job = "";
    var nameO = $('#txtNameO').val();
    if (nameO == '')
        nameO ="";
    var nameE = $('#txtNameE').val();
    if (nameE == '')
        nameE = "";


    return JSON.stringify({
        "nameOfCompany": nameO,
        "nameOfEmployee": nameE,
        "jobOfEmployee": job,
        "country": co,
        "page": page
    });
}

function showOrganizations(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    var i = 0;
    
    var orgDiv = "";
    $.each(list, function(index, org) {
        i++;
       orgDiv = orgDiv + "<div id=\"" + org.legalName + "\">\n\
<table><tbody><tr><td width=\"80%\"><h1><span>" + org.legalName + "</h1></td><td>\n\
    Web site:<h2><a href=\"" + org.legalName + "\">" + "<span>" + org.legalName + "</span></a></h2></td></tr>\n\
 <tr ><td colspan=\"2\"><h3>Founding date:</h3><span>" + org.foundingDate + "</span></td></tr>\n\
<tr ><td colspan=\"2\"><h3>Description:</h3><span>" + org.description + "</span></td></tr>\n\
 <tr><td colspan=\"2\"><h3>Addresses:</h3><span>" + returnAddresses(org.location) + "</span></td></tr>\n\
 <tr><td colspan=\"2\"><h3>Employee:</h3><span>" + returnEmployees(org.employee) + "</span></td></tr>\n\
</tr></tbody></table></div>";
   
});



    $('#searchResultDiv').html(orgDiv);
}

function returnEmployees(org) {
    var listEmp = org == null ? [] : (org instanceof Array ? org : [org]);
    var employeesDivs = "<div id=\"employees" + org.URI + "\" class=\"employeesdiv\">";

    $.each(listEmp, function(index, employees) {
        


         employeesDivs = employeesDivs +"<h4>Job title: <span>" + employees.jobTitle+" Name: "+employees.name + "</span></h4>";

    });
    employeesDivs = employeesDivs +"</div>";
    return employeesDivs;

}
function returnAddresses(org){
    var listAddr = org == null ? [] : (org instanceof Array ? org : [org]);
    var addressDivs = "<div id=\"address" + org.URI + "\" class=\"addressdiv\">";

    $.each(listAddr, function(index, address) {
        


        addressDivs = addressDivs +"<h4>Country: <span>" + address.addressCountry+" Street: "+address.streetAddress + "</span></h4>";

    });
    addressDivs = addressDivs +"</div>";
    return addressDivs;
    
    
}



function resetForm() {
    $("select").val(-1);
    $('#txtNameE').val("");
    $('#txtNameO').val("");
    search(1);
}


