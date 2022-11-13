package user.connect;

import entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import user.connect.exceptions.PendingRequestExistsException;
import user.connect.exceptions.UserAlreadyConnectedException;

import java.util.ArrayList;

class ConnectionVerifierTest {

    User user1;
    User user2;
    ConnectionVerifier verifier;
    @BeforeEach
    public void setUp() {
        ArrayList<String> user1Requests = new ArrayList<String>();
        ArrayList<String> user1PendingConnections = new ArrayList<String>();
        ArrayList<String> user1Connections = new ArrayList<String>();
        ArrayList<String> user2Requests = new ArrayList<String>();
        ArrayList<String> user2PendingConnections = new ArrayList<String>();
        ArrayList<String> user2Connections = new ArrayList<String>();

        user1 = new User("01", user1Requests, user1Connections, user1PendingConnections, "Leo", 0);
        user2 = new User("02", user2Requests, user2Connections, user2PendingConnections, "Alex", 0);
        verifier = new ConnectionVerifier(user1, user2);
    }

    @Test
    void testCheckAlreadyConnectedAlreadyConnected() {
        user1.getConnections().add("02");
        user2.getConnections().add("01");
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () -> verifier.checkAlreadyConnected());
        assertEquals("You are already connected!", exception.getMessage());
    }

    @Test
    void testCheckPendingRequestPendingRequestExists() {
        user2.getPendingConnections().add("01");
        Throwable exception = assertThrows(PendingRequestExistsException.class, () -> verifier.checkPendingRequest());
        assertEquals("Pending request!", exception.getMessage());
    }

    @Test
    void testCheckIncomingRequestIncomingRequestExists() {
        user1.getConnectionRequests().add("02");
        assertTrue(verifier.checkIncomingRequest());
    }

    @Test
    void testCheckIncomingRequestIncomingRequestDoesNotExist() {
        assertFalse(verifier.checkIncomingRequest());
    }

    @Test
    void testVerifyAlreadyConnected() {
        user1.getConnections().add("02");
        user2.getConnections().add("01");
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () -> verifier.checkAlreadyConnected());
        assertEquals("You are already connected!", exception.getMessage());
    }

    @Test
    void testVerifyPendingRequest() {
        user2.getPendingConnections().add("01");
        Throwable exception = assertThrows(PendingRequestExistsException.class, () -> verifier.checkPendingRequest());
        assertEquals("Pending request!", exception.getMessage());
    }
}