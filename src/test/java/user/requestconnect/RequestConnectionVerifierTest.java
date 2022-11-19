package user.requestconnect;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;

import java.util.ArrayList;

class RequestConnectionVerifierTest {

    User user1;
    User user2;
    RequestConnectionVerifier verifier;
    @BeforeEach
    public void setUp() {
        ArrayList<String> user1Requests = new ArrayList<String>();
        ArrayList<String> user1PendingConnections = new ArrayList<String>();
        ArrayList<String> user1Connections = new ArrayList<String>();
        ArrayList<String> user2Requests = new ArrayList<String>();
        ArrayList<String> user2PendingConnections = new ArrayList<String>();
        ArrayList<String> user2Connections = new ArrayList<String>();

        user1 = new User("01", user1Requests, user1Connections, user1PendingConnections,
                "leoliu", "leo@gmail.com", "pass", "Leo");
        user2 = new User("02", user2Requests, user2Connections, user2PendingConnections,
                "alex123", "alex@gmail.com", "pass", "Alex");
        verifier = new RequestConnectionVerifier(user1, user2);
    }

    @Test
    void testCheckAlreadyConnectedAlreadyConnected() {
        user1.getConnections().add(user2.getUsername());
        user2.getConnections().add(user1.getUsername());
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () -> verifier.checkAlreadyConnected());
        assertEquals("You are already connected!", exception.getMessage());
    }

    @Test
    void testCheckPendingRequestPendingRequestExists() {
        user2.getOutgoingConnectionRequests().add(user1.getUsername());
        Throwable exception = assertThrows(PendingRequestExistsException.class, () -> verifier.checkPendingRequest());
        assertEquals("Pending request!", exception.getMessage());
    }

    @Test
    void testCheckIncomingRequestIncomingRequestExists() {
        user1.getIncomingConnectionRequests().add(user2.getUsername());
        assertTrue(verifier.checkIncomingRequest());
    }

    @Test
    void testCheckIncomingRequestIncomingRequestDoesNotExist() {
        assertFalse(verifier.checkIncomingRequest());
    }

    @Test
    void testVerifyAlreadyConnected() {
        user1.getConnections().add(user2.getUsername());
        user2.getConnections().add(user1.getUsername());
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () -> verifier.checkAlreadyConnected());
        assertEquals("You are already connected!", exception.getMessage());
    }

    @Test
    void testVerifyPendingRequest() {
        user2.getOutgoingConnectionRequests().add(user1.getUsername());
        Throwable exception = assertThrows(PendingRequestExistsException.class, () -> verifier.checkPendingRequest());
        assertEquals("Pending request!", exception.getMessage());
    }
}