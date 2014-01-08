/**
 * Date: 6/12/2013
 * Time: 10:35 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */

package gr.fallout;

import gr.fallout.Models.Administrator;
import gr.fallout.Models.Supplier;
import gr.fallout.Store.RecordManager;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] p_Args) throws IOException
    {
        if (p_Args.length > 0 && p_Args[0].equalsIgnoreCase("-setup"))
            PerformSetup();

        System.out.println("The Fallout Robotics server has been initialized.");

        Fallout.GetInstance().Init(7423);
    }

    private static void PerformSetup()
    {
        Scanner s_Scanner = new Scanner(System.in);

        System.out.println("Please enter a username for the Administrator Account:");
        String s_Username = s_Scanner.nextLine();

        // TODO: Security concern!!! Password is visible on the terminal.
        System.out.println("Please enter a password for the Administrator Account:");
        String s_Password = s_Scanner.nextLine();

        Administrator s_Administrator = new Administrator();
        s_Administrator.Username(s_Username);
        s_Administrator.Password(s_Password);

        RecordManager.GetInstance().Administrators.Insert(s_Administrator);

        System.out.println("Administrator account successfully created. Creating Suppliers.");

        // Create Suppliers
        Supplier s_Supplier01 = new Supplier();
        s_Supplier01.Name("Intel");
        s_Supplier01.AvailableType(Supplier.PartType.CPU);
        s_Supplier01.EUBusiness(false);
        s_Supplier01.TaxID(123456789);
        s_Supplier01.Address("Santa Clara, California, United States");

        Supplier s_Supplier02 = new Supplier();
        s_Supplier02.Name("NZXT");
        s_Supplier02.AvailableType(Supplier.PartType.Case);
        s_Supplier02.EUBusiness(true);
        s_Supplier02.TaxID(987654321);
        s_Supplier02.Address("Warsaw, Poland");

        Supplier s_Supplier03 = new Supplier();
        s_Supplier03.Name("ACube Systems");
        s_Supplier03.AvailableType(Supplier.PartType.Motherboard);
        s_Supplier03.EUBusiness(true);
        s_Supplier03.TaxID(234567890);
        s_Supplier03.Address("Bassano del Grappa, Italy");

        Supplier s_Supplier04 = new Supplier();
        s_Supplier04.Name("Transcend Information");
        s_Supplier04.AvailableType(Supplier.PartType.RAM);
        s_Supplier04.EUBusiness(true);
        s_Supplier04.TaxID(102938475);
        s_Supplier04.Address("Taipei, Taiwan");

        RecordManager.GetInstance().Suppliers.Insert(s_Supplier01);
        RecordManager.GetInstance().Suppliers.Insert(s_Supplier02);
        RecordManager.GetInstance().Suppliers.Insert(s_Supplier03);
        RecordManager.GetInstance().Suppliers.Insert(s_Supplier04);

        System.out.println("Suppliers successfully created.");
    }
}
