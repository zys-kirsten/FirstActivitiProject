package zys.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadForm {

    private String filename = null;
    private CommonsMultipartFile file = null;

   
    public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public CommonsMultipartFile getFile() {
        return file;
    }
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
        this.filename = file.getOriginalFilename();
    }
}
