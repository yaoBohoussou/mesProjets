package mode_normal;

import java.util.ArrayList;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
public class Question
{
    private String id;
    private String enonce;
    private String reponse_correcte;
    private ArrayList<String> reponses_fausses;

    public Question(String id,String enonce,String reponse_correcte,ArrayList<String> reponses_fausses)
    {
        this.id = id;
        this.enonce=enonce;
        this.reponse_correcte=reponse_correcte;
        this.reponses_fausses=reponses_fausses;
    }

    public String getEnonce()
    {
        return this.enonce;
    }

    public String getReponse_correcte()
    {
        return this.reponse_correcte;
    }

    public String getReponse_fausse(int index)
    {
        return (this.reponses_fausses.get(index));
    }

    public ArrayList<String> getReponses_fausses()
    {
        return this.reponses_fausses;
    }
}