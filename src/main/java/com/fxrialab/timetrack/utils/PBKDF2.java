package com.fxrialab.timetrack.utils;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * Taken from https://asecuritysite.com/encryption/PBKDF2z
 * Created by Minh T. on 9/9/2018.
 */
class PBKDF2
{

    private Mac hMac = new HMac(new SHA1Digest());

    private void F(
            byte[] P,
            byte[] S,
            int c,
            byte[] iBuf,
            byte[] outBytes,
            int outOff)
    {
        byte[] state = new byte[hMac.getMacSize()];
        CipherParameters param = new KeyParameter(P);

        hMac.init(param);

        if (S != null)
        {
            hMac.update(S, 0, S.length);
        }

        hMac.update(iBuf, 0, iBuf.length);

        hMac.doFinal(state, 0);

        System.arraycopy(state, 0, outBytes, outOff, state.length);

        for (int count = 1; count != c; count++)
        {
            hMac.init(param);
            hMac.update(state, 0, state.length);
            hMac.doFinal(state, 0);

            for (int j = 0; j != state.length; j++)
            {
                outBytes[outOff + j] ^= state[j];
            }
        }
    }

    private void intToOctet(
            byte[] Buffer,
            int i)
    {
        Buffer[0] = (byte)((short)i >> 24);
        Buffer[1] = (byte)((short)i >> 16);
        Buffer[2] = (byte)((short)i >> 8);
        Buffer[3] = (byte)i;
    }

    // Use this function to retrieve a derived key.
    // dkLen is in octets, how much bytes you want when the function to return.
    // mPassword is the password converted to bytes.
    // mSalt is the salt converted to bytes
    // mIterationCount is the how much iterations you want to perform.


    public byte[] generateDerivedKey(
            int dkLen,
            byte[] mPassword,
            byte[] mSalt,
            int mIterationCount
    )
    {
        int hLen = hMac.getMacSize();
        int l = (dkLen + hLen - 1) / hLen;
        byte[] iBuf = new byte[4];
        byte[] outBytes = new byte[l * hLen];

        for (int i = 1; i <= l; i++)
        {
            intToOctet(iBuf, i);

            F(mPassword, mSalt, mIterationCount, iBuf, outBytes, (i - 1) * hLen);
        }

        //By this time outBytes will contain the derived key + more bytes.
        // According to the PKCS #5 v2.0: Password-Based Cryptography Standard (www.truecrypt.org/docs/pkcs5v2-0.pdf)
        // we have to "extract the first dkLen octets to produce a derived key".

        //I am creating a byte array with the size of dkLen and then using
        //Buffer.BlockCopy to copy ONLY the dkLen amount of bytes to it
        // And finally returning it :D

        byte[] output = new byte[dkLen];

        System.arraycopy(outBytes, 0, output, 0, dkLen);

        return output;
    }


}