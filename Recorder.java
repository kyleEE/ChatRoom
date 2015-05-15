/*
import javax.sound.sampled;

public class Recorder{
	private private float sampleRate;
    private int sampleSizeInBits;
    private int channels;
    private boolean signed;
    private boolean bigEndian;
	private boolean isRecord;
	Recorder(){
		sampleRate = 8000;
		sampleSizeInBits = 8;
		channels = 1;
		signed = true;
		bigEndian = true;
		isRecord=false;
		AudioFormat format =  new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		try{
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		TargetDataLine line = (TargetDataLine)AudioSystem.getLine(info);
		}
		catch(LineUnvailableException e){
			System.out.println("the TargetDataLine is unavailable	");
		}
		line.open(format);
		line.start();
	}
	public start(){
		
		int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
		byte buffer[] = new byte[bufferSize];
		out = new ByteArrayOutputStream();
		while (isRecord) {
			int count = line.read(buffer, 0, buffer.length);
			if (count > 0) {
				out.write(buffer, 0, count);
			}
		}
		out.close();
	}
}
*/
import com.github.sarxos.webcam.Webcam;

public class DetectWebcamExample {

	public static void main(String[] args) {
		Webcam webcam = Webcam.getDefault();
		if (webcam != null) {
			System.out.println("Webcam: " + webcam.getName());
		} else {
			System.out.println("No webcam detected");
		}
	}
}