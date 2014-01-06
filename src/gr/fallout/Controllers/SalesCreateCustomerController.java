package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.SalesCreateCustomerValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:09 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesCreateCustomerController extends ProtectedController<SalesManager>
{
    private SalesManager m_Manager;

    public SalesCreateCustomerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_sales_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
            return new ErrorResponse("Invalid method.");

        SalesCreateCustomerValidator s_Validator = new SalesCreateCustomerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new ErrorResponse(s_Errors.get(0));

        String s_Name = m_Params.get("name").get(0);
        String s_Username = m_Params.get("username").get(0);
        String s_Password = m_Params.get("password").get(0);
        String s_Address = m_Params.get("address").get(0);
        String s_Email = m_Params.get("email").get(0);
        Integer s_TaxID = Integer.parseInt(m_Params.get("taxid").get(0));
        Integer s_Phone = Integer.parseInt(m_Params.get("phone").get(0));
        Integer s_Fax = Integer.parseInt(m_Params.get("fax").get(0));

        Collection<Customer> s_Customers = RecordManager.GetInstance().Customers.GetAll();

        for (Customer s_Customer : s_Customers)
        {
            if (s_Customer.Username().equalsIgnoreCase(s_Username))
                return new ErrorResponse("The specified username is in use.");
            else if (s_Customer.Email().equalsIgnoreCase(s_Email))
                return new ErrorResponse("The specified email is in use.");
            else if (s_Customer.TaxID() == s_TaxID)
                return new ErrorResponse("The specified taxid is in use.");
        }

        Customer s_Customer = new Customer();
        s_Customer.Name(s_Name);
        s_Customer.Username(s_Username);
        s_Customer.Password(s_Password);
        s_Customer.Address(s_Address);
        s_Customer.Email(s_Email);
        s_Customer.TaxID(s_TaxID);
        s_Customer.Phone(s_Phone);
        s_Customer.Fax(s_Fax);

        RecordManager.GetInstance().Customers.Insert(s_Customer);

        //return new Response(new Gson().toJson(s_Manager));
        return new RedirectResponse(m_ContextBase);
    }
}
