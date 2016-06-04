package recibo;

public class Recibo {
	
	private Integer numeroDeGrupoPlan;
	private Integer cuotasPagas;
	
	public Recibo(Integer numeroDeUnGrupo){
		this.numeroDeGrupoPlan = numeroDeUnGrupo;
		this.cuotasPagas = 1;
	}

	public void sumarCuota(){
		this.cuotasPagas++;
	}

	public Integer getNumeroDeGrupo() {
		return numeroDeGrupoPlan;
	}

	public Integer getCuotasPagas() {
		return cuotasPagas;
	}
}
