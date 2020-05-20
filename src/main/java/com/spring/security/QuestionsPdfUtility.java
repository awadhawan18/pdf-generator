package com.spring.security;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class QuestionsPdfUtility {
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
  
    public static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("RelatedQuestion");
        document.addAuthor("Abhishek Wadhawan");
        document.addCreator("Abhishek Wadhawan");
    }

    public static void addTitle(Document document, String titleContent)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(titleContent, catFont));
        addEmptyLine(preface, 1);
        document.add(preface);
    }

    public static void addQuestion(Document document, String question) throws DocumentException {
        Paragraph paragraph = new Paragraph(question, subFont);
        addEmptyLine(paragraph, 2);
        document.add(paragraph);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
	

}
