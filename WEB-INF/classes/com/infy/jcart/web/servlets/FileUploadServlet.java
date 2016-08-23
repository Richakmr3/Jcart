package com.infy.jcart.web.servlets;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.infy.jcart.dao.VariantDAOImpl;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;
import com.infy.jcart.web.managedbeans.AddProductVariantBean;



public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1500 * 1024;
	private int maxMemSize = 40 * 1024;
	private File file;

	public void init() {
		filePath = JCartConfig.getPath("imageUrlPath");
	}

	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(JCartConfig.getPath("imageUrlPath")));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			Integer productId = null;
			/*String fit = null;
			String color = null;
			Integer stock = null;
			String url = "resources\\products\\";*/
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();

				if (!fi.isFormField()) {
					// get previous variationCode of this productId
					Factory.createVariantDAO()
							.getNextVariationCode(productId);

					// Get the uploaded file parameters
				
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					
					long sizeInBytes = fi.getSize();
					if (sizeInBytes > (40 * 1024)) {
						out
								.println("<center><p>Image Not Uploaded..<font color=\"red\">File Must be below 40KB</font></p></center>");

					}

					else if (contentType.substring(0, 5).equalsIgnoreCase(
							"image")
							&& contentType.substring(contentType.length() - 3)
									.equalsIgnoreCase("png")) {
						FacesContext ftx = FacesContext.getCurrentInstance();
						ExternalContext etx = ftx.getExternalContext();
						HttpSession session = (HttpSession) etx
								.getSession(true);

						AddProductVariantBean mb = (AddProductVariantBean) session
								.getAttribute("addProductVariantMB");
						if (mb != null) {
							String imageUrl = mb.getProductId()
									+ mb.getVariationCode()
									+ fileName.substring(fileName
											.lastIndexOf("."));
							file = new File(filePath + imageUrl);
							new VariantDAOImpl().addImage(mb.getProductId(), mb
									.getVariationCode(), imageUrl);
							fi.write(file);

							out
									.println("<center>Uploaded Filename: "
											+ mb.getProductId()
											+ mb.getVariationCode()
											+ fi.getContentType()
											+ "<br><br><a href=\"retailer/Home.jsp\">Home</a></center>");
						}
					} else {
						out.println("<html>");
						out.println("<head>");
						out.println("<title>Servlet upload</title>");
						out.println("</head>");
						out.println("<body>");
						out
								.println("<center><p>Image Not Uploaded..<font color=\"red\">File Must be an Image of type PNG</font></p></center>");
						out.println("</body>");
						out.println("</html>");
					}

				}

			}
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {

			if (null == ex.getMessage()
					|| ex.getMessage().equalsIgnoreCase("null")) {
			} else {
				JCartLogger.logError(this.getClass().getName(), "doPost",
						ex.toString());
			
				response.sendRedirect("../error.jsp");
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
}