package com.spring.security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class QuestionPdfGenerator {
	
	private static String FILE = "/Users/Abhishek.Wadhawan/Desktop/question.pdf";
	
	public String generatePdf(String searchQuery, List<String> questions) throws FileNotFoundException, DocumentException {
		
		 Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream(FILE));
         document.open();
         QuestionsPdfUtility.addTitle(document, "Here are some related questions to your search : " + searchQuery);
         for(String question : questions) {
        	 QuestionsPdfUtility.addQuestion(document, question);
         }
         
         document.close();
		
		return "Success";	
	}

}
