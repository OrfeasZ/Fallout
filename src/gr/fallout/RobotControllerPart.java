package gr.fallout;

/**
 * Created with IntelliJ IDEA.
 * User: NoFaTe
 * Date: 17/11/2013
 * Time: 11:35 μμ
 * To change this template use File | Settings | File Templates.
 */
public class RobotControllerPart {
    private int m_AssemblyCost;

    private int m_PurchaseCost;

    public int AssemblyCost(){
        return m_AssemblyCost;
    }

    public boolean AssemblyCost(int p_AssemblyCost){
        m_AssemblyCost=p_AssemblyCost;
        return true;

    }
    public int PurchaseCost(){
        return m_PurchaseCost;
    }

    public boolean PurchaseCost(int p_PurchaseCost){
        m_AssemblyCost=p_PurchaseCost;
        return true;

    }
}
