package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.ManagerDao;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Implementation of the ManagerService. The class is the part of implementation of
 * the business logic of the application.
 * @author 456519 Filip Lux
 * @version 11/16/2017.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerDao managerDao;

    @Inject
    public ManagerServiceImpl(ManagerDao managerDao) { this.managerDao = managerDao; }

    @Override
    public Manager fetchById(long managerId) {
        return managerDao.fetchById(managerId);
    }

    @Override
    public Manager fetchByName(String name) {
        return managerDao.fetchByName(name);
    }

    @Override
    public Manager fetchByEmail(String email) {
        return managerDao.fetchByEmail(email);
    }

    @Override
    public Manager fetchByTeam(Team team) {
        return managerDao.fetchByTeam(team);
    }

    @Override
    public List<Manager> fetchByNationality(NationalityEnum nationality) {
        return managerDao.fetchByNationality(nationality);
    }

    @Override
    public List<Manager> fetchManagersWithoutTeam() {
        return managerDao.fetchManagersWithoutTeam();
    }

    @Override
    public List<Manager> fetchAdmins() {
        return managerDao.fetchAdmins();
    }

    @Override
    public List<Manager> fetchAll() {
        return managerDao.fetchAll();
    }

    @Override
    public boolean isAdmin(Manager manager) {
        return managerDao.fetchAdmins().contains(manager);
    }

    @Override
    public Manager create(Manager manager, String unencryptedPassword) throws SoccerManagerServiceException {

        if (managerDao.fetchAll().contains(manager)) {
            throw new SoccerManagerServiceException(
                    "Manager " + manager.getName()  + " already exists");
        } else {
            manager.setPasswordHash(createHash(unencryptedPassword));
            managerDao.insert(manager);
            return manager;
        }
    }

    @Override
    public void update(Manager manager) throws SoccerManagerServiceException {

        if (!managerDao.fetchAll().contains(manager)) {
            throw new SoccerManagerServiceException(
                    "Manager " + manager.getName() + " do not exists.");
        }
        managerDao.update(manager);
    }

    @Override
    public void remove(long managerId) {
        managerDao.delete(managerId);

    }

    @Override
    public boolean authenticate(Manager manager, String unecryptedPassword) {
        return validatePassword(unecryptedPassword, manager.getPasswordHash());
    }

    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
