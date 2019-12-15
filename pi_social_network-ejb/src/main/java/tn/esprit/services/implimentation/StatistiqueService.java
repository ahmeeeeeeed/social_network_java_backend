package tn.esprit.services.implimentation;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Claim;
import tn.esprit.entities.Competence;
import tn.esprit.entities.Interview;
import tn.esprit.entities.Offre;
import tn.esprit.entities.Payement;
import tn.esprit.entities.User;
import tn.esprit.services.interfaces.StatistiqueServiceRemote;


@Stateless
public class StatistiqueService implements StatistiqueServiceRemote{

	@PersistenceContext
	EntityManager em;
	private Offre[] tabs;
	
	@Override
	public Map<String,Integer>  doStatNbrRecrutementEntrepriseChaqueMois( int idCompanyManager) {
		TypedQuery<Interview> query =em.createQuery("select i from Interview i "+
				"where idCompanyManager=:idCompanyManager",Interview.class);
		query.setParameter("idCompanyManager", idCompanyManager);

		Map<String,Integer> map = new TreeMap<>();
		
		map.put("janvier", 0);
		map.put("février", 0);
		map.put("mars", 0);
		map.put("avril", 0);
		map.put("mai", 0);
		map.put("juin", 0);
		map.put("juillet", 0);
		map.put("août", 0);
		map.put("septembre", 0);
		map.put("octobre", 0);
		map.put("novembre", 0);
		map.put("décembre", 0);
		for(Interview o : query.getResultList()) {
			if(o.getCandidate().getAfftected()== 1) {
				switch (o.getDate().getMonth()) {
				case 0:map.put("janvier", map.get("janvier")+1);break;
				case 1:map.put("février", map.get("février")+1);break;
				case 2:map.put("mars", map.get("mars")+1);break;
				case 3:map.put("avril", map.get("avril")+1);break;
				case 4:map.put("mai", map.get("mai")+1);break;
				case 5:map.put("juin", map.get("juin")+1);break; 
				case 6:map.put("juillet", map.get("juillet")+1);break;
				case 7:map.put("août", map.get("août")+1);break;
				case 8:map.put("septembre", map.get("septembre")+1);break;
				case 9:map.put("octobre", map.get("octobre")+1);break;
				case 10:map.put("novembre", map.get("novembre")+1);break;
				case 11:map.put("décembre", map.get("décembre")+1);break;

			default:
				break;
			}
			}
			
			
		}

return map;
		
	}
	
	
	@Override
	public int doStatNbrRecrutementEntrepriseParMois(int mois, int idCompanyManager) {
		Date thisMounth = new Date();
		Date lastMaount = new Date();
		Date nextMaount = new Date();
		thisMounth.setMonth(mois);
		lastMaount.setMonth(thisMounth.getMonth()-1);lastMaount.setDate(28);
		nextMaount.setMonth(thisMounth.getMonth()+1);nextMaount.setDate(1);
		
		
		TypedQuery<Interview> query =em.createQuery("select i from Interview i "+
		"where idCompanyManager=:idCompanyManager and i.date >:lastMaount and i.date <:nextMaount",Interview.class);
		query.setParameter("idCompanyManager", idCompanyManager);
		query.setParameter("lastMaount", lastMaount,TemporalType.DATE);
		query.setParameter("nextMaount", nextMaount,TemporalType.DATE);
		
		List<Interview> listinterview = query.getResultList(); 
		int c= 0;
		
	//	System.out.println("************************  "+listinterview.size());
		
		for(Interview i : listinterview) {
			
			
			if(i.getCandidate().getAfftected()== 1)
				c++;
		}
		System.out.println(c);
		return c;
		
	}

	
	@Override
	public int doStatNbrToutRecrutementParMois(int mois) {
		
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date thisMounth = new Date();
		Date lastMaount = new Date();
		Date nextMaount = new Date();
		thisMounth.setMonth(mois);
		lastMaount.setMonth(thisMounth.getMonth()-1);lastMaount.setDate(28);
		nextMaount.setMonth(thisMounth.getMonth()+1);nextMaount.setDate(1);
		//System.out.println(sdf.format(thisMounth));
		
		
		TypedQuery<Interview> query =em.createQuery("select i from Interview i "+
		"where i.date >:lastMaount and i.date <:nextMaount",Interview.class);
		
		query.setParameter("lastMaount", lastMaount,TemporalType.DATE);
		query.setParameter("nextMaount", nextMaount,TemporalType.DATE);
		
		List<Interview> listinterview = query.getResultList(); 
		int c= 0;
		
	//	System.out.println("************************  "+listinterview.size());
		
		for(Interview i : listinterview) {
			
			System.out.println(i.getDate());
			if(i.getCandidate().getAfftected()== 1)
				c++;
		}
		return c;
		
	}

	
	
	//************************** à compléter *******************************************************//
	//9adech mn we7d cherk f recrutement ando skill skill eli talbinha entrprise
	//exempl : java :5, node:4, symfony: 10, autre : 11
	@Override
	public void doStatUserSkillsMostRequiredPerMounth(String listcompetence,int mois) {

		Date thisMounth = new Date();
		Date lastMaount = new Date();
		Date nextMaount = new Date();
		thisMounth.setMonth(mois);
		lastMaount.setMonth(thisMounth.getMonth()-1);lastMaount.setDate(28);
		nextMaount.setMonth(thisMounth.getMonth()+1);nextMaount.setDate(1);
		//System.out.println(sdf.format(thisMounth));
		
		
		TypedQuery<Interview> query =em.createQuery("select i from Interview i "+
		"where i.date >:lastMaount and i.date <:nextMaount",Interview.class);
		
		query.setParameter("lastMaount", lastMaount,TemporalType.DATE);
		query.setParameter("nextMaount", nextMaount,TemporalType.DATE);
		
		List<Interview> listinterview = query.getResultList();
		String Competances = "";
		
		 Map<Integer,String[]> map = new LinkedHashMap<>();
		 
		 System.out.println(listinterview.size());
		
			System.out.println("*************************************************************************************");
		for(Interview i : listinterview) {

		//System.out.println(i.getDate());
		if(i.getCandidate().getAfftected()== 0) {/*
			TypedQuery<Competence> queryCompet = em.createQuery("select c from competence c  where c.candidate_id = candidate_id",Competence.class);
			queryCompet.setParameter("candidate_id", i.getCandidate().getId());
			Competence compet = queryCompet.getSingleResult();
			
			
			System.out.println("****************"+compet.getCompetences());
			
			String[] competSplitted = compet.getCompetences().split(";");
			
			map.put(i.getCandidate().getId(),competSplitted);*/
			//System.out.println("*********************************************"+i.getDate());
			for(Competence c : i.getCandidate().getCompetences()) {
				
				map.put(i.getCandidate().getId(),c.getCompetences().split(";"));
			}
		}

				
		}
		for(java.util.Map.Entry<Integer, String[]>  entry : map.entrySet()) {
			System.out.println("id : "+entry.getKey());
			System.out.println("value : "+entry.getValue().length);
		}
		//return null;
	}
	
	//*********************************************************************************************************//


	@Override
	public int getNbrPublicationOffreParMois(int idUser,int mois) {
		User u = em.find(User.class, idUser);
		int c = 0;
		for (Offre o : u.getOffre()) {
			
			if(o.getDate().getMonth() == mois)
				c++;
			
		}
		return c;
	
	}
	
	@Override
	public Map<String,Integer> getNbrPublicationChaqueMois() {

		TypedQuery<Offre> query =em.createQuery("select o from Offre o ",Offre.class);
				Map<String,Integer> map = new TreeMap<>();
				
				map.put("janvier", 0);
				map.put("février", 0);
				map.put("mars", 0);
				map.put("avril", 0);
				map.put("mai", 0);
				map.put("juin", 0);
				map.put("juillet", 0);
				map.put("août", 0);
				map.put("septembre", 0);
				map.put("octobre", 0);
				map.put("novembre", 0);
				map.put("décembre", 0);
				for(Offre o : query.getResultList()) {
					
					switch (o.getDate().getMonth()) {
						case 0:map.put("janvier", map.get("janvier")+1);break;
						case 1:map.put("février", map.get("février")+1);break;
						case 2:map.put("mars", map.get("mars")+1);break;
						case 3:map.put("avril", map.get("avril")+1);break;
						case 4:map.put("mai", map.get("mai")+1);break;
						case 5:map.put("juin", map.get("juin")+1);break;
						case 6:map.put("juillet", map.get("juillet")+1);break;
						case 7:map.put("août", map.get("août")+1);break;
						case 8:map.put("septembre", map.get("septembre")+1);break;
						case 9:map.put("octobre", map.get("octobre")+1);break;
						case 10:map.put("novembre", map.get("novembre")+1);break;
						case 11:map.put("décembre", map.get("décembre")+1);break;

					default:
						break;
					}
					
				}

		return map;
	}
/**
	@Override
	public int getNbrPublicationParSemaine(int idUser ,int semaine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbrPublicationParJour(int idUser,int jour) {
		
		TypedQuery<Post> query = em.createQuery("select p from Post p  join p.activite a "
				+ "where  p.description LIKE 'migration%'",Payement.class);

		List<Payement> lisp = query.getResultList();
		return 0;
	}*/
	@Override
	public int getNbrUserPostuleAOffre(int idOffre) {
				User us = em.find(User.class, 1);
				for (Offre oo :us.getOffre()) {
					System.out.println(oo.getId());
				}

		Offre o = em.find(Offre.class, idOffre);
		System.out.println("offre : "+o.getContenu());
		int c = 0;
		for (User u : o.getUser()) {
			System.out.println(u.getId());

			//if(u instanceof User) {
				c++;
			//}
				
			
		}
		return c;
	}

	@Override
	public Map<String,Integer>  getNbrPayementPremiumChaqueMois() {
		
		TypedQuery<Payement> query = em.createQuery("select p from Payement p  "
				+ "where  p.description LIKE 'migration%'",Payement.class);

		//List<Payement> lisp = query.getResultList();
		//return lisp.size();
		
		
		Map<String,Integer> map = new TreeMap<>();
		
		map.put("janvier", 0);
		map.put("février", 0);
		map.put("mars", 0);
		map.put("avril", 0);
		map.put("mai", 0);
		map.put("juin", 0);
		map.put("juillet", 0);
		map.put("août", 0);
		map.put("septembre", 0);
		map.put("octobre", 0);
		map.put("novembre", 0);
		map.put("décembre", 0);
		for(Payement o : query.getResultList()) {
			
			switch (o.getDatePayement().getMonth()) {
				case 0:map.put("janvier", map.get("janvier")+1);break;
				case 1:map.put("février", map.get("février")+1);break;
				case 2:map.put("mars", map.get("mars")+1);break;
				case 3:map.put("avril", map.get("avril")+1);break;
				case 4:map.put("mai", map.get("mai")+1);break;
				case 5:map.put("juin", map.get("juin")+1);break;
				case 6:map.put("juillet", map.get("juillet")+1);break;
				case 7:map.put("août", map.get("août")+1);break;
				case 8:map.put("septembre", map.get("septembre")+1);break;
				case 9:map.put("octobre", map.get("octobre")+1);break;
				case 10:map.put("novembre", map.get("novembre")+1);break;
				case 11:map.put("décembre", map.get("décembre")+1);break;

			default:
				break;
			}
			
		}
		return map;
	}


	@Override
	public Offre[] getListOffreParCompany(int idCompany) {
		TypedQuery<Offre> query =em.createQuery("select o from Offre o",Offre.class);
		//return query.getResultList();
		
		
		List<Offre> listo = new ArrayList<Offre>();
		for (Offre p : query.getResultList()) 
		{
			List<User> listu = new ArrayList<User>();
			for (User pu : p.getUser()) {
				if(pu.getId()==idCompany)
				listu.add(new User(pu.getId(),pu.getPrenom(),pu.getNom(),pu.getAdresse(),pu.getTypeCompte(),pu.getMail(),pu.getPassword()
						,pu.getIsActive(),pu.getNbAbonnees()));
			}
			listo.add(new Offre(p.getId(), p.getContenu(), p.getDate(), p.getNiveauExp(),listu));
		}
		
		//return listo;
		
		tabs = new Offre[listo.size()];
		int x =0;
		
		for (Offre p :  listo) 
				{
					tabs[x] = p;
					x++;		
				}
		
		for (int i = 0; i < tabs.length ; i++)  
        {
             int index = i;  
             for (int j = i + 1; j < tabs.length; j++)
             {
                  if (tabs[j].getDate().compareTo(tabs[index].getDate())>0){ 
                       index = j;
                  }
             }

             Offre min = tabs[index]; 
             tabs[index] = tabs[i]; 
             tabs[i] = min;
             
        }
		
		return tabs;
		
		
		
		
	}
	
	


}
