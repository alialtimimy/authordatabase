import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Author {
    private  String Institution;
    private  String email;
    private  String phone;
    private  String citations;
    private  String hindex;

    public String getInstitution() {
        return "Institution: " + Institution;
    }

    public String getEmail() {
        return "email: " + email;
    }

    public String getPhone() {
        return "phone: " + phone;
    }

    public String getCitations() {
        return "citations: " + citations;
    }

    public String getHindex() {
        return "hindex: " + hindex;
    }

    public String getI10index() {
        return "i10index: " + i10index;
    }

    public String getInfluencescore() {
        return "influencescore: " + influencescore;
    }

    private  String i10index;
    private  String influencescore;

    public Author(ResultSet result) throws SQLException {
        ArrayList<String> array = new ArrayList<String>();
        while (result.next()) {
            Institution = result.getString("Institution").trim();
            email = result.getString("email").trim();
            phone = result.getString("phone").trim();
            citations = result.getString("citations").trim();
            hindex = result.getString("hindex").trim();
            i10index = result.getString("i10index").trim();
            influencescore = result.getString("influencescore").trim();
        }
    }
    public ArrayList<String> getList(){
        return new ArrayList<String>(Arrays.asList(new String[]{getInstitution(), getEmail(), getPhone(), getCitations(), getHindex(), getI10index(), getInfluencescore()}));
    }
}
