package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesFromZipParsingTests {

  private ClassLoader cl = FilesFromZipParsingTests.class.getClassLoader();


  @Test
  void XlsFileFromZipParsingTest() throws Exception {
    try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("exampleHW.zip"))) {
      ZipEntry entry;

      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().endsWith(".xls")) {

          XLS xls = new XLS(zis);

          Assertions.assertEquals(1.0, xls.excel.getSheetAt(0).getRow(1).getCell(0).getNumericCellValue());
          Assertions.assertEquals("Dulce", xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue());
          Assertions.assertEquals("Abril", xls.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue());
          Assertions.assertEquals("Female", xls.excel.getSheetAt(0).getRow(1).getCell(3).getStringCellValue());
          Assertions.assertEquals("United States", xls.excel.getSheetAt(0).getRow(1).getCell(4).getStringCellValue());
          Assertions.assertEquals(32.0, xls.excel.getSheetAt(0).getRow(1).getCell(5).getNumericCellValue());
          Assertions.assertEquals("15/10/2017", xls.excel.getSheetAt(0).getRow(1).getCell(6).getStringCellValue());
          Assertions.assertEquals(1562.0, xls.excel.getSheetAt(0).getRow(1).getCell(7).getNumericCellValue());

        }
      }
    }
  }

  @Test
  void PdfFileFromZipParsingTest() throws Exception {
    try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("exampleHW.zip"))) {
      ZipEntry entry;

      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().endsWith(".pdf")) {

          PDF pdf = new PDF(zis);
          System.out.println(pdf);
          Assertions.assertEquals("sample", pdf.title);
          Assertions.assertTrue(pdf.text.contains("This is a simple PDF file. Fun fun fun."));
        }
      }
    }
  }

  @Test
  void CsvFileFromZipParsingTest() throws Exception {
    try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("exampleHW.zip"))) {
      ZipEntry entry;

      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().endsWith(".csv")) {
          CSVReader csvReader = new CSVReader(new InputStreamReader(zis));

          List<String[]> data = csvReader.readAll();

          for (int i = 0; i < data.size(); i++) {  //цикл: инициализация, условия завершения, изменения
            System.out.println(data.get(i));
          }


          Assertions.assertEquals(6, data.size());
          Assertions.assertArrayEquals(
                  new String[]{"Username;Identifier;First name;Last name"},
                  data.get(0)
          );
          Assertions.assertArrayEquals(
                  new String[]{"booker12;9012;Rachel;Booker"},
                  data.get(1)
          );
        }
      }
    }
  }
}

