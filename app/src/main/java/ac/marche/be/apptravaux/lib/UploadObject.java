package ac.marche.be.apptravaux.lib;

/**
 * Created by jfsenechal on 07-12-16.
 * https://inducesmile.com/android/android-upload-image-to-server-using-retrofit-2/
 */
public class UploadObject {
    private String success;
    public UploadObject(String success) {
        this.success = success;
    }
    public String getSuccess() {
        return success;
    }
}
