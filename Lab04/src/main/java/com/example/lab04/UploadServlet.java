package com.example.lab04;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@WebServlet(name = "uploadServlet", value="/uploadServlet")
@MultipartConfig(
//        location = "D:\\CN_JAVA\\TH\\Ex\\CN_JAVA\\Lab04\\src\\main\\fileUpload",
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class UploadServlet extends HttpServlet {
    private static String[] fileExtensionAccept = {"png","jpg","txt", "doc", "docx", "img", "pdf", "rar", "zip"};
    private static final String UPLOAD_DIRECTORY = "uploads";


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationPath = getServletContext().getRealPath("");
        System.out.println(applicationPath);
        resp.sendRedirect("upload.jsp");
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String nameSave = request.getParameter("name");
            // Get upload directory path
            String applicationPath = getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY;

            // Create uploads directory if not exists
            File uploadDirectory = new File(uploadFilePath);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            // Get the parts from request
            for (Part part : request.getParts()) {
                // Extract file name
                String fileName = getFileName(request);

                // Check if the file has a supported extension
                String fileExtension = getFileExtension(fileName);
                if (!isValidFileExtension(fileExtension)) {
                    response.getWriter().write("Unsupported file extension");
                    return;
                }

                // Check if the file already exists
                File existingFile = new File(uploadFilePath + File.separator + nameSave+"."+fileExtension);
                boolean overrideIfExists = request.getParameter("overrideIfExists") != null;
                if (existingFile.exists() && !overrideIfExists) {
                    response.getWriter().write("File already exists");
                    return;
                }

                // Save the file
                try (InputStream input = part.getInputStream()) {
                    Files.copy(input, Paths.get(uploadFilePath, nameSave+"."+fileExtension), StandardCopyOption.REPLACE_EXISTING);
                }

                // Display success message
                File folder = new File(uploadFilePath);
                if(folder.exists() && folder.isDirectory()) {
                    File[] files = folder.listFiles();

                    if(files != null) {
                        for (File f: files) {
                            System.out.println(f.getName() +"&"+ f.getPath());
                        }
                        request.setAttribute("files", files);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/fileUploaded.jsp");
                         dispatcher.forward(request, response);
            }
        }
                response.getWriter().write("File has been uploaded. <a href='DownloadServlet?fileName=" + nameSave + "'>Click here to visit file</a>");
            }
        }

        // Utility method to get file name from HTTP header content-disposition
        private String getFileName(HttpServletRequest req) throws ServletException, IOException {
            Part file = req.getPart("file");
            String fileName = file.getSubmittedFileName();
            return fileName;
        }

        // Utility method to get file extension
        private String getFileExtension(String fileName) {
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        }

        // Utility method to check if the file extension is valid
        private boolean isValidFileExtension(String extension) {

            return Arrays.asList(fileExtensionAccept).contains(extension.toLowerCase());
        }
}
