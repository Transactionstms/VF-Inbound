<%-- 
    Document   : customForm
    Created on : 27/02/2023, 11:55:23 AM
    Author     : Desarrollo Tacts
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String part3 = par[2];

    //Extracción de Hora   
    Date hr = new Date();
    DateFormat hours = new SimpleDateFormat("HH:mm:ss");
    String hora = hours.format(hr);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Modificar GTN</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- Prism Syntax Highlighting-->
        <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
        <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="../lib/css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="../lib/img/favicon.png">
        <!-- Table css -->
        <link href="../lib/inbound/gtn/styleGTN.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
        <style>
            /*input {
              border-top-style: hidden;
              border-right-style: hidden;
              border-left-style: hidden;
              border-bottom-style: hidden;
              background-color: #ffffff;
            }

            .no-outline:focus {
              outline: none;
            }*/
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

                ConsultasQuery fac = new ConsultasQuery();
        %>
        <!-- navbar-->
        <header class="header">
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!--<div class="unwired alert alert-danger">¡Se ha perdido su conexión! TMS debe de estar conectado a Internet para su correcto funcionamiento.</div>-->
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Modificar GTN</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form class="row g-3 needs-validation" novalidate="">
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Valor USD</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">ETA Port Of Discharge</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Agente Aduanal</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Pedimento A1</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Pedimento R1</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Motivo rectificación 1</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Pedimento R1 (2do)</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Motivo rectificación 2</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Recepción Documentos</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Revalidación/Liberación de BL</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Previo Origen</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Previo en destino</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Resultado Previo</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Proforma Final</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Requiere permiso Si/No</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha envío Fichas/notas</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fec. Recepción de permisos tramit.</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fec. Act Permisos (Inic Vigencia)</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fec. Perm. Aut. (Fin de Vigencia)</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Cuenta con CO para aplicar preferencia Arancelaria Si/No</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Aplico Preferencia Arancelaria (CO) Si/No Razon</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Requiere UVA Si/No</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Documentos Completos</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Pago Pedimento</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Solicitud de transporte</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Modulacion</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Modalidad Camion/Tren</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Resultado Modulacion Verde / Rojo</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Reconocimiento</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha Liberacion</label>
                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Sello Origen</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Sello Final</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fecha de retencion por la autoridad</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Fec. de liberacion por ret. de la aut.</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Estatus de la operación</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom01">Motivo Atraso</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                              <div class="col-md-12">
                                                <label class="form-label" for="validationCustom01">Observaciones</label>
                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                              </div>
                                             
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              <hr>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom04">Country</label>
                                                <select class="form-select" id="validationCustom04" required="">
                                                  <option selected="" disabled="" value="">Choose...</option>
                                                  <option value="AF">Afghanistan</option>
                                                  <option value="AX">Åland Islands</option>
                                                  <option value="AL">Albania</option>
                                                  <option value="DZ">Algeria</option>
                                                  <option value="AS">American Samoa</option>
                                                  <option value="AD">AndorrA</option>
                                                  <option value="AO">Angola</option>
                                                  <option value="AI">Anguilla</option>
                                                  <option value="AQ">Antarctica</option>
                                                  <option value="AG">Antigua and Barbuda</option>
                                                  <option value="AR">Argentina</option>
                                                  <option value="AM">Armenia</option>
                                                  <option value="AW">Aruba</option>
                                                  <option value="AU">Australia</option>
                                                  <option value="AT">Austria</option>
                                                  <option value="AZ">Azerbaijan</option>
                                                  <option value="BS">Bahamas</option>
                                                  <option value="BH">Bahrain</option>
                                                  <option value="BD">Bangladesh</option>
                                                  <option value="BB">Barbados</option>
                                                  <option value="BY">Belarus</option>
                                                  <option value="BE">Belgium</option>
                                                  <option value="BZ">Belize</option>
                                                  <option value="BJ">Benin</option>
                                                  <option value="BM">Bermuda</option>
                                                  <option value="BT">Bhutan</option>
                                                  <option value="BO">Bolivia</option>
                                                  <option value="BA">Bosnia and Herzegovina</option>
                                                  <option value="BW">Botswana</option>
                                                  <option value="BV">Bouvet Island</option>
                                                  <option value="BR">Brazil</option>
                                                  <option value="IO">British Indian Ocean Territory</option>
                                                  <option value="BN">Brunei Darussalam</option>
                                                  <option value="BG">Bulgaria</option>
                                                  <option value="BF">Burkina Faso</option>
                                                  <option value="BI">Burundi</option>
                                                  <option value="KH">Cambodia</option>
                                                  <option value="CM">Cameroon</option>
                                                  <option value="CA">Canada</option>
                                                  <option value="CV">Cape Verde</option>
                                                  <option value="KY">Cayman Islands</option>
                                                  <option value="CF">Central African Republic</option>
                                                  <option value="TD">Chad</option>
                                                  <option value="CL">Chile</option>
                                                  <option value="CN">China</option>
                                                  <option value="CX">Christmas Island</option>
                                                  <option value="CC">Cocos (Keeling) Islands</option>
                                                  <option value="CO">Colombia</option>
                                                  <option value="KM">Comoros</option>
                                                  <option value="CG">Congo</option>
                                                  <option value="CD">Congo, The Democratic Republic of the</option>
                                                  <option value="CK">Cook Islands</option>
                                                  <option value="CR">Costa Rica</option>
                                                  <option value="CI">Cote D'Ivoire</option>
                                                  <option value="HR">Croatia</option>
                                                  <option value="CU">Cuba</option>
                                                  <option value="CY">Cyprus</option>
                                                  <option value="CZ">Czech Republic</option>
                                                  <option value="DK">Denmark</option>
                                                  <option value="DJ">Djibouti</option>
                                                  <option value="DM">Dominica</option>
                                                  <option value="DO">Dominican Republic</option>
                                                  <option value="EC">Ecuador</option>
                                                  <option value="EG">Egypt</option>
                                                  <option value="SV">El Salvador</option>
                                                  <option value="GQ">Equatorial Guinea</option>
                                                  <option value="ER">Eritrea</option>
                                                  <option value="EE">Estonia</option>
                                                  <option value="ET">Ethiopia</option>
                                                  <option value="FK">Falkland Islands (Malvinas)</option>
                                                  <option value="FO">Faroe Islands</option>
                                                  <option value="FJ">Fiji</option>
                                                  <option value="FI">Finland</option>
                                                  <option value="FR">France</option>
                                                  <option value="GF">French Guiana</option>
                                                  <option value="PF">French Polynesia</option>
                                                  <option value="TF">French Southern Territories</option>
                                                  <option value="GA">Gabon</option>
                                                  <option value="GM">Gambia</option>
                                                  <option value="GE">Georgia</option>
                                                  <option value="DE">Germany</option>
                                                  <option value="GH">Ghana</option>
                                                  <option value="GI">Gibraltar</option>
                                                  <option value="GR">Greece</option>
                                                  <option value="GL">Greenland</option>
                                                  <option value="GD">Grenada</option>
                                                  <option value="GP">Guadeloupe</option>
                                                  <option value="GU">Guam</option>
                                                  <option value="GT">Guatemala</option>
                                                  <option value="GG">Guernsey</option>
                                                  <option value="GN">Guinea</option>
                                                  <option value="GW">Guinea-Bissau</option>
                                                  <option value="GY">Guyana</option>
                                                  <option value="HT">Haiti</option>
                                                  <option value="HM">Heard Island and Mcdonald Islands</option>
                                                  <option value="VA">Holy See (Vatican City State)</option>
                                                  <option value="HN">Honduras</option>
                                                  <option value="HK">Hong Kong</option>
                                                  <option value="HU">Hungary</option>
                                                  <option value="IS">Iceland</option>
                                                  <option value="IN">India</option>
                                                  <option value="ID">Indonesia</option>
                                                  <option value="IR">Iran, Islamic Republic Of</option>
                                                  <option value="IQ">Iraq</option>
                                                  <option value="IE">Ireland</option>
                                                  <option value="IM">Isle of Man</option>
                                                  <option value="IL">Israel</option>
                                                  <option value="IT">Italy</option>
                                                  <option value="JM">Jamaica</option>
                                                  <option value="JP">Japan</option>
                                                  <option value="JE">Jersey</option>
                                                  <option value="JO">Jordan</option>
                                                  <option value="KZ">Kazakhstan</option>
                                                  <option value="KE">Kenya</option>
                                                  <option value="KI">Kiribati</option>
                                                  <option value="KP">Korea, Democratic People'S Republic of</option>
                                                  <option value="KR">Korea, Republic of</option>
                                                  <option value="KW">Kuwait</option>
                                                  <option value="KG">Kyrgyzstan</option>
                                                  <option value="LA">Lao People'S Democratic Republic</option>
                                                  <option value="LV">Latvia</option>
                                                  <option value="LB">Lebanon</option>
                                                  <option value="LS">Lesotho</option>
                                                  <option value="LR">Liberia</option>
                                                  <option value="LY">Libyan Arab Jamahiriya</option>
                                                  <option value="LI">Liechtenstein</option>
                                                  <option value="LT">Lithuania</option>
                                                  <option value="LU">Luxembourg</option>
                                                  <option value="MO">Macao</option>
                                                  <option value="MK">Macedonia, The Former Yugoslav Republic of</option>
                                                  <option value="MG">Madagascar</option>
                                                  <option value="MW">Malawi</option>
                                                  <option value="MY">Malaysia</option>
                                                  <option value="MV">Maldives</option>
                                                  <option value="ML">Mali</option>
                                                  <option value="MT">Malta</option>
                                                  <option value="MH">Marshall Islands</option>
                                                  <option value="MQ">Martinique</option>
                                                  <option value="MR">Mauritania</option>
                                                  <option value="MU">Mauritius</option>
                                                  <option value="YT">Mayotte</option>
                                                  <option value="MX">Mexico</option>
                                                  <option value="FM">Micronesia, Federated States of</option>
                                                  <option value="MD">Moldova, Republic of</option>
                                                  <option value="MC">Monaco</option>
                                                  <option value="MN">Mongolia</option>
                                                  <option value="MS">Montserrat</option>
                                                  <option value="MA">Morocco</option>
                                                  <option value="MZ">Mozambique</option>
                                                  <option value="MM">Myanmar</option>
                                                  <option value="NA">Namibia</option>
                                                  <option value="NR">Nauru</option>
                                                  <option value="NP">Nepal</option>
                                                  <option value="NL">Netherlands</option>
                                                  <option value="AN">Netherlands Antilles</option>
                                                  <option value="NC">New Caledonia</option>
                                                  <option value="NZ">New Zealand</option>
                                                  <option value="NI">Nicaragua</option>
                                                  <option value="NE">Niger</option>
                                                  <option value="NG">Nigeria</option>
                                                  <option value="NU">Niue</option>
                                                  <option value="NF">Norfolk Island</option>
                                                  <option value="MP">Northern Mariana Islands</option>
                                                  <option value="NO">Norway</option>
                                                  <option value="OM">Oman</option>
                                                  <option value="PK">Pakistan</option>
                                                  <option value="PW">Palau</option>
                                                  <option value="PS">Palestinian Territory, Occupied</option>
                                                  <option value="PA">Panama</option>
                                                  <option value="PG">Papua New Guinea</option>
                                                  <option value="PY">Paraguay</option>
                                                  <option value="PE">Peru</option>
                                                  <option value="PH">Philippines</option>
                                                  <option value="PN">Pitcairn</option>
                                                  <option value="PL">Poland</option>
                                                  <option value="PT">Portugal</option>
                                                  <option value="PR">Puerto Rico</option>
                                                  <option value="QA">Qatar</option>
                                                  <option value="RE">Reunion</option>
                                                  <option value="RO">Romania</option>
                                                  <option value="RU">Russian Federation</option>
                                                  <option value="RW">RWANDA</option>
                                                  <option value="SH">Saint Helena</option>
                                                  <option value="KN">Saint Kitts and Nevis</option>
                                                  <option value="LC">Saint Lucia</option>
                                                  <option value="PM">Saint Pierre and Miquelon</option>
                                                  <option value="VC">Saint Vincent and the Grenadines</option>
                                                  <option value="WS">Samoa</option>
                                                  <option value="SM">San Marino</option>
                                                  <option value="ST">Sao Tome and Principe</option>
                                                  <option value="SA">Saudi Arabia</option>
                                                  <option value="SN">Senegal</option>
                                                  <option value="CS">Serbia and Montenegro</option>
                                                  <option value="SC">Seychelles</option>
                                                  <option value="SL">Sierra Leone</option>
                                                  <option value="SG">Singapore</option>
                                                  <option value="SK">Slovakia</option>
                                                  <option value="SI">Slovenia</option>
                                                  <option value="SB">Solomon Islands</option>
                                                  <option value="SO">Somalia</option>
                                                  <option value="ZA">South Africa</option>
                                                  <option value="GS">South Georgia and the South Sandwich Islands</option>
                                                  <option value="ES">Spain</option>
                                                  <option value="LK">Sri Lanka</option>
                                                  <option value="SD">Sudan</option>
                                                  <option value="SR">Suriname</option>
                                                  <option value="SJ">Svalbard and Jan Mayen</option>
                                                  <option value="SZ">Swaziland</option>
                                                  <option value="SE">Sweden</option>
                                                  <option value="CH">Switzerland</option>
                                                  <option value="SY">Syrian Arab Republic</option>
                                                  <option value="TW">Taiwan, Province of China</option>
                                                  <option value="TJ">Tajikistan</option>
                                                  <option value="TZ">Tanzania, United Republic of</option>
                                                  <option value="TH">Thailand</option>
                                                  <option value="TL">Timor-Leste</option>
                                                  <option value="TG">Togo</option>
                                                  <option value="TK">Tokelau</option>
                                                  <option value="TO">Tonga</option>
                                                  <option value="TT">Trinidad and Tobago</option>
                                                  <option value="TN">Tunisia</option>
                                                  <option value="TR">Turkey</option>
                                                  <option value="TM">Turkmenistan</option>
                                                  <option value="TC">Turks and Caicos Islands</option>
                                                  <option value="TV">Tuvalu</option>
                                                  <option value="UG">Uganda</option>
                                                  <option value="UA">Ukraine</option>
                                                  <option value="AE">United Arab Emirates</option>
                                                  <option value="GB">United Kingdom</option>
                                                  <option value="US">United States</option>
                                                  <option value="UM">United States Minor Outlying Islands</option>
                                                  <option value="UY">Uruguay</option>
                                                  <option value="UZ">Uzbekistan</option>
                                                  <option value="VU">Vanuatu</option>
                                                  <option value="VE">Venezuela</option>
                                                  <option value="VN">Viet Nam</option>
                                                  <option value="VG">Virgin Islands, British</option>
                                                  <option value="VI">Virgin Islands, U.S.</option>
                                                  <option value="WF">Wallis and Futuna</option>
                                                  <option value="EH">Western Sahara</option>
                                                  <option value="YE">Yemen</option>
                                                  <option value="ZM">Zambia</option>
                                                  <option value="ZW">Zimbabwe</option>
                                                </select>
                                                <div class="valid-feedback">Looks good!</div>
                                                <div class="invalid-feedback">Please select a country.</div>
                                              </div>
                                              <div class="col-md-3">
                                                <label class="form-label" for="validationCustom05">Zip</label>
                                                <input class="form-control" id="validationCustom05" type="text" required="">
                                                <div class="invalid-feedback">Please provide a valid zip.</div>
                                              </div>
                                              <div class="col-12">
                                                <div class="form-check"></div>
                                                <input class="form-check-input" id="invalidCheck" type="checkbox" value="" required="">
                                                <label class="form-check-label" for="invalidCheck">Agree to terms and conditions</label>
                                                <div class="invalid-feedback">You must agree before submitting.</div>
                                              </div>
                                              <div class="col-12">
                                                <button class="btn btn-primary" type="submit">Submit form</button>
                                              </div>
                                            
                                            
                                            
                                            
                                            <br>
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                                <a class="btn btn-default text-nowrap" role="button" href="Importacion/gtn.jsp">Regresar</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Guardar Información</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>   
                    </section>
                </div>  
                
                <!-- modal - Vista Previa CFDI --> 
                <div class="modal fade text-start" id="modalVistaPreviaDetails" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Vista previa detalles:</h3>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container" id="visor1"> 
                                    Detalles
                                </div>
                            </div>
                        </div>
                    </div>
                </div>  
                
                <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start fw-bold">
                                <p class="mb-2 mb-md-0 fw-bold">Transactions TMS &copy; <%=part3%></p>
                            </div>
                            <div class="col-md-6 text-center text-md-end text-gray-400">
                                <p class="mb-0">Version 1.1.0</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>                       
        <!-- Conexión estatus red -->                    
        <script src="../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>  
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Main Theme JS File-->
        <script src="../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../lib/vendor/prismjs/prism.js"></script>
        <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- actions js -->
        <script src="../lib/inbound/gtn/functionsGtn.js" type="text/javascript"></script>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <script type="text/javascript">
            // Optional
            Prism.plugins.NormalizeWhitespace.setDefaults({
                'remove-trailing': true,
                'remove-indent': true,
                'left-trim': true,
                'right-trim': true,
            });

        </script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
