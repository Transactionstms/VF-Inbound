/**
 * @author himanshu_agnihotri
 * This class populates the jasper file with the values from request xml file 
 * and export the global label report into pdf form.
 */
package com.dhl.sop.label;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.dhl.datatypes.Dutiable;
import com.dhl.datatypes.DutyTaxPaymentType;
import com.dhl.datatypes.Reference;
import com.dhl.datatypes.ShipValResponsePiece;
import com.dhl.datatypes.SpecialService;
import java.util.Map;

//import org.apache.commons.collections.BidiMap; 
//import org.apache.commons.collections.bidimap.DualHashBidiMap;


public class LabelReportController {

}