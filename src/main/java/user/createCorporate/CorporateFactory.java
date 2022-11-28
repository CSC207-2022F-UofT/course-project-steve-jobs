package user.createCorporate;

import entity.Corporate;
import entity.CorporateRep;

public class CorporateFactory {

    /**
     * This CorporateFactory can produce Corporate objects.
     * @param owner is the CorporateRep user who manages this Corporate
     * @param companyName is the name of the company
     * @param companyInfo is a summary of the company
     * @return a Corporate object that is newly created
     */

    public Corporate create(CorporateRep owner, String companyName, String companyInfo) {
        return new Corporate(owner, companyName, companyInfo);
    }

}
