package useCases.createCorporatePage;

// not needed
public class PageGeneratePresenter {

    public PageGenerateResponseModel prepareSuccessView(PageGenerateResponseModel response){
        return response;
    }

    public PageGenerateResponseModel prepareFailView(String error){
        throw new PageCreationFailed(error);
    }


}
