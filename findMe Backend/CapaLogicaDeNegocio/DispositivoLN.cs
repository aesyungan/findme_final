using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CapaAccesoAtos;
using CapaEntidades;

namespace CapaLogicaDeNegocio
{
    public class DispositivoLN
    {


        #region "Patron Singleton"
        private static DispositivoLN dispositivoLN = null;
        private DispositivoLN() { }
        public static DispositivoLN getInstance()
        {
            if (dispositivoLN == null)
            {
                dispositivoLN = new DispositivoLN();


            }
            return dispositivoLN;
        }

        #endregion
        public Dispositivo BuscarPorImei(String imei)
        {
            Dispositivo returnItem = null;
            try
            {
                foreach (Dispositivo item in Listar())
                {
                    if (item.imei==imei)
                    {
                        returnItem = item;
                    }
                }
            }
            catch (Exception ex)
            {

                throw ex;
            }

            return returnItem;
        }
        public List<Dispositivo> Listar()
        {
            try
            {
                return DispositivoAD.getInstance().Listar();
            }
            catch (Exception ex)
            {

                throw ex;
            }


        }
        public int Ingreso(Dispositivo item)
        {
            try
            {
                return DispositivoAD.getInstance().Ingreso(item);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }

        public int Update(Dispositivo item)
        {
            try
            {
                return DispositivoAD.getInstance().Update(item);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }
        public int Delete(Dispositivo item)
        {
            try
            {
                return DispositivoAD.getInstance().Delete(item);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }

    }
}
