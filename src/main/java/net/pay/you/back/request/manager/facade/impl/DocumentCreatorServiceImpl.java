package net.pay.you.back.request.manager.facade.impl;

import java.io.*;
import java.util.*;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import net.pay.you.back.request.manager.comm.DocumentCreator;
import net.pay.you.back.request.manager.config.AwsS3Config;
import net.pay.you.back.request.manager.dao.DocumentVersionDAO;
import net.pay.you.back.request.manager.domain.DocVersion;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.domain.user.User;
import net.pay.you.back.request.manager.facade.DocumentCreatorService;
import net.pay.you.back.request.manager.facade.LoanProcessingService;
import net.pay.you.back.request.manager.facade.UserService;
import net.pay.you.back.request.manager.service.SequenceGeneratorService;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class DocumentCreatorServiceImpl implements DocumentCreatorService {
    private static final Logger logger = LogManager.getLogger(DocumentCreatorServiceImpl.class);
    public static final String MIME_TYPE = "application/pdf";
    @Autowired
    private AwsS3Config s3Config;

    @Autowired
    private UserService userService;

    @Autowired
    private LoanProcessingService loanProcessingService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private DocumentVersionDAO documentVersionDAO;

    @Autowired
    @Qualifier("freeMarkerConfigurationFactoryBean")
    private Configuration config;

    @Autowired
    @Qualifier("webApplicationContext")
    private ResourceLoader resourceLoader;

    private String template;
    private Map templateModel;

    @Override
    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public void setTemplateModel(Loan templateModel) {
        Resource resource = resourceLoader.getResource("classpath:app_logo.png");
        Map<String, Object> model = new HashMap<>();
        model.put("loan", templateModel);

        try {
            model.put("app_logo_src", resource.getURI());
        }
        catch (IOException e) {
            logger.error(e);
        }
        this.templateModel = model;
    }

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

                cell = new PdfPCell(new Phrase(null != lenderLoanDetail.getRepaymentDate() ? lenderLoanDetail.getRepaymentDate().toString() : "2021-04-02T10:45:00.000+00:00"));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingLeft(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(null != lenderLoanDetail.getApprovedTimeStamp() ? lenderLoanDetail.getApprovedTimeStamp().toString() : "2019-04-02T10:45:00.000+00:00"));
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
            document.add(new Phrase("\n"));
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
        saveDocumentInAws(inputStream, lender.getFirstName() + " " + lender.getLastName());
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream generatePDFFromTmpl() {
        String parsed_html = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            parsed_html = FreeMarkerTemplateUtils.processTemplateIntoString(config.getTemplate(template), this.templateModel);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(parsed_html);
            renderer.layout();
            renderer.createPDF(out);
            out.close();

        } catch (IOException | TemplateException | com.lowagie.text.DocumentException e) {
            logger.error(e.getMessage());
        }
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
//        saveDocumentInAws(inputStream, lender.getFirstName() + " " + lender.getLastName());
        return new ByteArrayInputStream(out.toByteArray());
    }


    private void saveDocumentInAws(ByteArrayInputStream inputStream, String lenderName) {
        try {
            AWSCredentials credentials = new BasicAWSCredentials(s3Config.getAccesskey(), s3Config.getSecretkey());
            AmazonS3 s3Client = new AmazonS3Client(credentials);

            String bucketPath = s3Config.getBucketpath();
            InputStream is = new BufferedInputStream(inputStream);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(is.available());
            String fileName = new StringBuilder(lenderName).append(".pdf").toString();
            PutObjectResult result = s3Client.putObject(new PutObjectRequest(bucketPath, fileName, is, meta).withCannedAcl(CannedAccessControlList.Private));
            String docVersionId = result.getVersionId();
            saveDocVersionDetails(docVersionId);

        } catch (Exception ex) {
            logger.error("Error while saving pdf document on aws s3 box ", ex);
        }
    }

    private void saveDocVersionDetails(String docVersionId) {
        try {
            DocVersion documentVersion = new DocVersion();
            documentVersion.setId(sequenceGeneratorService.generateSequence(DocVersion.SEQUENCE_NAME));
            documentVersion.setDocumentVersion(docVersionId);
            documentVersion.setMimeType(MIME_TYPE);
            documentVersionDAO.save(documentVersion);
        } catch (Exception ex) {
            logger.error("Error while saving document version details in mongo db ", ex);
        }
    }

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
