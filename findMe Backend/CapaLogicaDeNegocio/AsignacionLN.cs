using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CapaEntidades;
using CapaAccesoAtos;

namespace CapaLogicaDeNegocio
{
   public class AsignacionLN
    {
        #region "Patron Singleton"
        private static AsignacionLN asignacionLN = null;
        private AsignacionLN() { }
        public static AsignacionLN getInstance()
        {
            if (asignacionLN == null)
            {
                asignacionLN = new AsignacionLN();
            }
            return asignacionLN;
        }

        #endregion

        public List<Asignacion> Listar() {
            try
            {
                return AsignacionAD.getInstance().Listar();
            }
            catch (Exception ex)
            {

                throw ex;
            }

        }


        public List<Asignacion> Logueo(Usuario user)
        {
            try
            {
                return AsignacionAD.getInstance().Logueo(user);
            }
            catch (Exception ex)
            {

                throw ex;
            }

        }
        public int Ingreso(Asignacion item) {
            try
            {
                return AsignacionAD.getInstance().Ingreso(item);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }
        public int Update(Asignacion item) {
            try
            {
                return AsignacionAD.getInstance().Update(item);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }
        public int Remove(Asignacion item) {
            try
            {
                return AsignacionAD.getInstance().Remove(item);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }

    }
}
