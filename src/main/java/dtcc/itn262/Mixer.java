package dtcc.itn262;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import java.util.Arrays;
import java.util.List;

public class Mixer {

    private List<String> notes = Arrays.asList("C", "D", "E", "F", "G", "A", "B");
    private MidiChannel[] channels;
    private int INSTRUMENT = 15; // so it'll play like a piano
    private int VOLUME = 220;

    public Mixer() {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();
            for (int x = 1; x < 7; x++) {
                // play some sound
                String num = String.valueOf(x);
                play(num + " ", 450);
                play(num + " E", 300);
                play(num + " F", 300);
                play(num + " E", 1300);
                rest(500);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void play(String note, int duration) throws InterruptedException {
        channels[INSTRUMENT].noteOn(id(note), VOLUME);
        Thread.sleep(duration);
        channels[INSTRUMENT].noteOff(id(note));
    }

    private void rest(int duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    private int id(String note) {
        int octave = Integer.parseInt(note.substring(0, 1)); //grab the first position in the string
        // which is a number need to put a number before each not in order to play
        return notes.indexOf(note.substring(1)) + 12 * octave + 12;
    }

}
