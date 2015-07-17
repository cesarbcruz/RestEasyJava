package logic.covabra.erp;

import java.io.File;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

    @Path("/fileserver")
public class FileService {

    @GET
    @Path("/get")
    @Produces("application/json")
    public Product getProductInJSON() {

        Product product = new Product();
        product.setName("iPad 3");
        product.setQty(999);

        return product;

    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(Product product) {

        String result = "Product created : " + product;
        return Response.status(201).entity(result).build();

    }
    
    
    @GET
    @Path("/{fileName}/download")
    public Response getFileInTextFormat(@PathParam("fileName") String fileName) {
        System.out.println("File requested is : " + fileName);

        //Put some validations here such as invalid file name or missing file name
        if (fileName == null || fileName.isEmpty()) {
            ResponseBuilder response = Response.status(Status.BAD_REQUEST);
            return response.build();
        }

        //Prepare a file object with file to return
        File file = new File("/home/cesar/"+fileName);

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"howtodoinjava.txt\"");
        return response.build();
    }
    
    @GET
    @Path("/{fileName}/pdf")
    @Produces("application/pdf")
    public Response getFileInPDFFormat(@PathParam("fileName") String fileName) {
        System.out.println("File requested is : " + fileName);

        //Put some validations here such as invalid file name or missing file name
        if (fileName == null || fileName.isEmpty()) {
            ResponseBuilder response = Response.status(Status.BAD_REQUEST);
            return response.build();
        }

        //Prepare a file object with file to return
        File file = new File("c:/demoPDFFile.pdf");

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"howtodoinjava.pdf\"");
        return response.build();
    }

    @GET
    @Path("/{fileName}/image")
    @Produces("image/jpeg")
    public Response getFileInJPEGFormat(@PathParam("fileName") String fileName) {
        System.out.println("File requested is : " + fileName);

        //Put some validations here such as invalid file name or missing file name
        if (fileName == null || fileName.isEmpty()) {
            ResponseBuilder response = Response.status(Status.BAD_REQUEST);
            return response.build();
        }

        //Prepare a file object with file to return
        File file = new File("c:/demoJpegFile.jpeg");

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"howtodoinjava.jpeg\"");
        return response.build();
    }
}