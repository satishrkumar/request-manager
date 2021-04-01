package net.pay.you.back.request.manager.facade;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.pay.you.back.request.manager.comm.DocumentCreator;
import net.pay.you.back.request.manager.domain.Loan;
import net.pay.you.back.request.manager.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class DocumentCreatorServiceImpl implements DocumentCreatorService {
    @Autowired
    private UserService userService;

    @Autowired
    private LoanProcessingService loanProcessingService;

    @Override
    public ByteArrayInputStream createDocument(String lenderEmailId) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        User lender = userService.findUserByEmailId(lenderEmailId);
        List<Loan> lenderLoanDetails = loanProcessingService.findLoanDetailsByLenderEmailId(lenderEmailId);
        try {

            PdfWriter.getInstance(document, out);
            String pdfContent = "Dear " + lender.getFirstName() + " " + lender.getLastName() + " below are your loan lending details";

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{20, 20, 10, 20, 20, 15});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Lender Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Borrower Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Interest Rate", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Agreement Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Completion Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Repayment Frequency", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Loan lenderLoanDetail : lenderLoanDetails) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(lenderLoanDetail.getLenderEmailId()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(lenderLoanDetail.getBorrowerEmailId()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(null != lenderLoanDetail.getApr() ? lenderLoanDetail.getApr().toString() : "2.0"));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(null != lenderLoanDetail.getRepaymentDate() ? lenderLoanDetail.getRepaymentDate().toString() : "2021-04-02T10:45:00.000+00:00" ));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingLeft(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(null !=  lenderLoanDetail.getApprovedTimeStamp() ? lenderLoanDetail.getApprovedTimeStamp().toString() : "2019-04-02T10:45:00.000+00:00"));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(null != lenderLoanDetail.getRepayFrequency() ? lenderLoanDetail.getRepayFrequency().toString() : "Monthly"));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

            }
            document.open();
            Chunk chunk = new Chunk(pdfContent, headFont);

            document.add(chunk);
            document.add( new Phrase("\n") );
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

//    private void saveDocumentInAws(){
//        AWSCredentials credentials = new BasicAWSCredentials(appId,appSecret);
//        AmazonS3 s3Client = new AmazonS3Client(credentials);
//
//        String bucketPath = "YOUR_BUCKET_NAME/FOLDER_INSIDE_BUCKET";
//        InputStream is = new FileInputStream("YOUR_PDF_FILE_PATH");
//        ObjectMetadata meta = new ObjectMetadata();
//        meta.setContentLength(is.available());
//        s3Client.putObject(new PutObjectRequest(bucketPath,"YOUR_FILE.pdf", is, meta).withCannedAcl(CannedAccessControlList.Private));
//    }

    @Override
    public DocumentCreator findDocumentByEmailId(String emailId) {
        return null;
    }

    @Override
    public DocumentCreator updateExistingDocument(DocumentCreator documentCreator) {
        return null;
    }

    @Override
    public String deleteDocument(String emailId) {
        return null;
    }
}
