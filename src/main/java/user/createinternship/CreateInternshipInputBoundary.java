package user.createinternship;

public interface CreateInternshipInputBoundary {

    CreateInternshipResponseDS createInternship(CreateInternshipInputDS inputDS);

    void test_update_review();
}