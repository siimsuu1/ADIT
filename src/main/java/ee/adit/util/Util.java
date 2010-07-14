package ee.adit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.log4j.Logger;
import org.castor.core.util.Base64Encoder;

public class Util {

	private static Logger LOG = Logger.getLogger(Util.class);
	
	public static String base64encode(String string) throws UnsupportedEncodingException {
		return new String(Base64Encoder.encode(string.getBytes("UTF-8")));
	}
	
	public static String generateGUID() {
    	return java.util.UUID.randomUUID().toString();
    }
	
	public static String generateRandomFileName() {
		StringBuffer result = new StringBuffer();
		Random r = new Random();
		for(int i = 0; i < 30; i++) {
			result.append(r.nextInt(10));
		}
		result.append(".adit");
		return result.toString();
	}
	
	public static String generateRandomFileNameWithoutExtension() {
		StringBuffer result = new StringBuffer();
		Random r = new Random();
		for(int i = 0; i < 30; i++) {
			result.append(r.nextInt(10));
		}
		return result.toString();
	}
	
	public static String generateRandomID() {
		StringBuffer result = new StringBuffer();
		Random r = new Random();
		for(int i = 0; i < 30; i++) {
			result.append(r.nextInt(10));
		}
		return result.toString();
	}
		
	public static String gzipAndBase64Encode(String inputFile, String tempDir, boolean deleteTemporaryFiles) throws IOException {
		String resultFileName = null;
		
		// Pack data to GZip format
        String zipOutFileName = inputFile + "_zipOutBuffer.adit";
        LOG.debug("Packing data to GZip format. Output file: " + zipOutFileName);
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream zipOutFile = new FileOutputStream(zipOutFileName, false);
        GZIPOutputStream out = new GZIPOutputStream(zipOutFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
        	LOG.debug(new String(buf, "UTF-8"));
            out.write(buf, 0, len);
        }
        in.close();
        out.finish();
        out.close();
        LOG.debug("GZip complete");
        
        // Encode the GZipped data to Base64 binary data
        resultFileName = inputFile + "_Base64OutBuffer.adit";
        LOG.debug("Encoding zip file to Base64: Output file: " + zipOutFileName);
        in = new FileInputStream(zipOutFileName);
        FileOutputStream b64out = new FileOutputStream(resultFileName, false);
        Base64OutputStream b64outStream = new Base64OutputStream(b64out);
        byte[] b = new byte[66000];
        while ((len = in.read(b)) > 0) {
        	b64outStream.write(b, 0, len);
        }
        in.close();
        b64outStream.close();
        b64out.close();
        
        // Delete temporary files
        try {
        	if(deleteTemporaryFiles) {
        		File zipFile = new File(zipOutFileName);
            	zipFile.delete();
            	LOG.debug("Deleted temporary file: " + zipOutFileName);
        	}        	
        } catch(Exception e) {
        	LOG.error("Exception while deleting temporary files: ", e);
        }
        
		return resultFileName;
	}
	
	public static String base64EncodeFile(String inputFile, String tempDir) throws IOException {
		String resultFileName = null;
		
        // Encode data to Base64 binary data
        resultFileName = inputFile + "_Base64OutBuffer.adit";
        LOG.debug("Encoding file to Base64: Output file: " + inputFile);
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream b64out = new FileOutputStream(resultFileName, false);
        Base64OutputStream b64outStream = new Base64OutputStream(b64out);
        byte[] b = new byte[66000];
        int len = 0;
        while ((len = in.read(b)) > 0) {
        	b64outStream.write(b, 0, len);
        }
        in.close();
        b64outStream.close();
        b64out.close();

        return resultFileName;
	}
	
	public static String base64DecodeAndUnzip(String inputFile, String tempDir, boolean deleteTemporaryFiles) throws IOException {
		
		// Base64 decode
		String base64DecodedFile = inputFile + "_Base64DecodedOutBuffer.adit";
		FileInputStream inputFileStream = new FileInputStream(inputFile);
		FileOutputStream base64DecodedOut = new FileOutputStream(base64DecodedFile, false);
		Base64InputStream base64InputStream = new Base64InputStream(inputFileStream);
		
		int len;
		byte[] b = new byte[66000];
        while ((len = base64InputStream.read(b)) > 0) {
        	base64DecodedOut.write(b, 0, len);
        }
        base64DecodedOut.close();
        base64InputStream.close();
        inputFileStream.close();
		
        // Unzip
        
        String unzipOutFileName = inputFile + "_unzipOutBuffer.adit";
        FileOutputStream unzipOutFileStream = new FileOutputStream(unzipOutFileName, false);
        FileInputStream gzipFileInputStream = new FileInputStream(base64DecodedFile);
        GZIPInputStream gzipInputStream = new GZIPInputStream(gzipFileInputStream);
        
        byte[] buf = new byte[1024];
        while ((len = gzipInputStream.read(buf)) > 0) {
        	unzipOutFileStream.write(buf, 0, len);
        }
        unzipOutFileStream.close();
        gzipInputStream.close();
        gzipFileInputStream.close();        
        
        // Delete temporary files
        try {
        	if(deleteTemporaryFiles) {
        		File zipFile = new File(base64DecodedFile);
            	zipFile.delete();
            	LOG.debug("Deleted temporary file: " + base64DecodedFile);
        	}        	
        } catch(Exception e) {
        	LOG.error("Exception while deleting temporary files: ", e);
        }
        
        return unzipOutFileName;
	}
	
	public static String base64DecodeFile(String inputFile, String tempDir) throws IOException {
		
		// Base64 decode
		String base64DecodedFile = inputFile + "_Base64DecodedOutBuffer.adit";
		FileInputStream inputFileStream = new FileInputStream(inputFile);
		FileOutputStream base64DecodedOut = new FileOutputStream(base64DecodedFile, false);
		Base64InputStream base64InputStream = new Base64InputStream(inputFileStream);
		
		int len;
		byte[] b = new byte[66000];
        while ((len = base64InputStream.read(b)) > 0) {
        	base64DecodedOut.write(b, 0, len);
        }
        base64DecodedOut.close();
        base64InputStream.close();
        inputFileStream.close();
		
        return base64DecodedFile;
	}
	
	public static String createTemporaryFile(InputStream inputStream, String tempDir) throws IOException {		
		
		String temporaryFile = tempDir + File.separator + generateRandomFileName();
		FileOutputStream fileOutputStream = new FileOutputStream(temporaryFile);
		
		int len;
		byte[] buf = new byte[1024];
		while((len = inputStream.read(buf)) > 0) {
			fileOutputStream.write(buf, 0, len);
		}
		
		fileOutputStream.close();
		inputStream.close();
		
		return temporaryFile;
	}
	
	public static boolean deleteFile(String fileName, boolean deleteTemporaryFiles) {
		if(deleteTemporaryFiles) {
			boolean fileDeleted = (new File(fileName)).delete();
			if(fileDeleted) {
				LOG.debug("Deleted temporary file: " + fileName);
			} else {
				LOG.warn("Could not delete temporary file: " + fileName);
			}
			return fileDeleted;
		} else {
			return false;
		}
	}
	
	public static void printHeader(CustomXTeeHeader header) {
		
		LOG.debug("-------- XTeeHeader --------");
		
		LOG.debug("Nimi: " + header.getNimi());
		LOG.debug("ID: " + header.getId());
		LOG.debug("Isikukood: " + header.getIsikukood());
		LOG.debug("Andmekogu: " + header.getAndmekogu());
		LOG.debug("Asutus: " + header.getAsutus());
		LOG.debug("Allasutus: " + header.getAllasutus());
		LOG.debug("Amet: " + header.getAmet());
		LOG.debug("Infosüsteem: " + header.getInfosysteem());
		
		LOG.debug("----------------------------");
	}
	
	public static String convertToHexString(final byte[] byteArray) {
		final byte[] HEXES = "0123456789ABCDEF".getBytes();
		if (byteArray == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * byteArray.length);
		for (final byte b : byteArray) {
			hex.append(HEXES[(b >> 4) & 0xF]).append(HEXES[(b ) & 0xF]);
		}
		return hex.toString();
	}
	
    public static boolean deleteDir(File dir) {
        if (dir == null) {
        	return true;
        }
        if (!dir.exists()) {
        	return true;
        }
    	if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
