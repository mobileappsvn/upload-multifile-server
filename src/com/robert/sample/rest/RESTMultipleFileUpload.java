package com.robert.sample.rest;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.robert.config.Constant;
import com.robert.config.StringApiConstant;
import com.robert.config.StringConstant;
import com.robert.ws.message2.Message;
import com.wordnik.swagger.annotations.Api;

@Path(StringApiConstant.MODEL_UPLOAD_PREFIX)
@Api(value = StringApiConstant.MODEL_UPLOAD_PREFIX, description = StringConstant.UPLOAD_MODEL)
public class RESTMultipleFileUpload {
	private static final String FILE_UPLOAD_PATH = "/Library/WebServer/Documents/upload_multi_files/uploads";

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path(StringApiConstant.API_UPLOAD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerWebService(@Context HttpServletRequest request) {
		
		String candidateName = null;

		// checks whether there is a file upload request or not
		if (ServletFileUpload.isMultipartContent(request)) {
			final FileItemFactory factory = new DiskFileItemFactory();
			final ServletFileUpload fileUpload = new ServletFileUpload(factory);
			try {
				/*
				 * parseRequest returns a list of FileItem
				 */
				final List items = fileUpload.parseRequest(request);

				if (items != null) {
					final Iterator iter = items.iterator();
					while (iter.hasNext()) {
						final FileItem item = (FileItem) iter.next();
						final String itemName = item.getName();
						final String fieldName = item.getFieldName();
						final String fieldValue = item.getString();

						if (item.isFormField()) {
							candidateName = fieldValue;
							System.out.println("Field Name: " + fieldName + ", Field Value: " + fieldValue);
							System.out.println("Candidate Name: " + candidateName);
						} else {
							final File savedFile = new File(FILE_UPLOAD_PATH + File.separator + itemName);
							System.out.println("Saving the file: " + savedFile.getName());
							item.write(savedFile);
						}

					}
				}
			} catch (FileUploadException fue) {
				System.out.println("Returned Response Status failed!");
				fue.printStackTrace();
				Message error = new Message(Constant.UPLOAD_FAIL, StringConstant.UPLOAD_FAIL);
				return Response.ok().entity(error).build();
			} catch (Exception e) {
				System.out.println("Returned Response Status failed!");
				e.printStackTrace();
				Message error = new Message(Constant.UPLOAD_FAIL, StringConstant.UPLOAD_FAIL);
				return Response.ok().entity(error).build();
			}
		}

		System.out.println("Returned Response Status sucessful!");
		
		Message error = new Message(Constant.UPLOAD_SUCCESSFUL, StringConstant.UPLOAD_SUCCESSFUL);
		return Response.ok().entity(error).build();
		
	}
}
