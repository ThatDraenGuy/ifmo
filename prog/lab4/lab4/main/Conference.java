package main;
import people.*;
import main.*;
import rocket.*;
public class Conference {
	protected Traveller traveller;
	protected Scientist[] scientists;
	protected Journalist[] journalists;
	protected Rocket rocket;
	public Conference(Traveller t, Scientist[] s, Journalist[] j, Rocket r) {
		this.traveller=t;
		this.scientists=s;
		this.journalists=j;
		this.rocket=r;
	}
	public void start() {
		System.out.println("The Conference is starting!");
		traveller.tell(rocket);
		for (Scientist i : scientists) {
			i.ask(traveller);
		}
		for (Journalist i : journalists) {
			i.ask(traveller);
		}
		System.out.println("...The conference went on and on for hours... Until eventually it was over.");
	}
	public class Broadcast {
		protected TVWatcher[] tvwatchers;
		public Broadcast(TVWatcher[] tv) {
			this.tvwatchers=tv;
		}
		public void start() {
			System.out.println("");
			System.out.println("The broadcast is starting.");
			for (Scientist i : scientists) {
				System.out.print(i.getSpeciality()+" "+i.getName()+" and ");
			}
			System.out.println("Traveller "+traveller.getName()+"are talking about Big Earth's sky");
			for (TVWatcher i : tvwatchers) {
				System.out.print(i.getName()+" and ");
			}
			System.out.println("much more are enjoying the broadcast!");
		}
	}
}