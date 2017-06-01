package mode_normal;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yann BOHOUSSOU on 25/04/2016.
 */
public class Partie
{
    private HashMap<String , Integer> avancement;
    private String theme;
    private ArrayList<Integer> questions; // liste de questions déja posées

    public Partie(String theme, int niveau)
    {
        this.theme = theme;
        this.avancement = new HashMap<String , Integer>();
        this.avancement.put(this.theme,new Integer(niveau));
        questions = new ArrayList<Integer>();
    }

    public String getTheme()
    {return this.theme;}

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public int getAvancement()
    {
        return this.avancement.get(this.theme).intValue();
    }

    public void setAvancement(int niveau)
    {
        this.avancement.put(this.theme, new Integer(niveau));
    }

    public boolean checkIfAlreadyAsked(Integer num_Question)
    {
        return this.questions.contains(num_Question);
    }

    public void addAskedQuestion(Integer num_Question)
    {
        this.questions.add(num_Question);
    }
}

