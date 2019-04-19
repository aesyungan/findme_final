using CapaEntidades;
using CapaAccesoAtos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaLogicaDeNegocio
{
    public class UsuarioLN
    {

        #region "Patron Singleton"
        private static UsuarioLN usuarioLN = null;
        private UsuarioLN() { }
        public static UsuarioLN getInstance()
        {
            if (usuarioLN == null)
            {
                usuarioLN = new UsuarioLN();


            }
            return usuarioLN;
        }

        #endregion
        public Usuario VerificarUsuario(Usuario usuario)
        {
            try
            {
                return UsuarioDAO.getInstance().VerificarUsuario(usuario);
            }
            catch (Exception ex)
            {

                throw ex;
            }
           

        }


        public int Ingreso(Usuario usuario) {
            try
            {
                return UsuarioDAO.getInstance().Ingreso(usuario);
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }

        public List<Usuario> Listar() {
            try
            {
                return UsuarioDAO.getInstance().Listar();
            }
            catch (Exception ex)
            {

                throw ex;
            }

        }

        public Usuario buscarUsuario_UserName(string username)
        {
            Usuario returnUser = null;
            try
            {

                foreach (Usuario item in Listar())
                {
                    if (item.usuario==username)
                    {
                        returnUser = item;
                    }
                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            return returnUser;
        }

        public int Eliminar(Usuario usuario)
        {
            try
            {
                return UsuarioDAO.getInstance().Eliminar(usuario);
            }
            catch (Exception ex)
            {

                throw ex;
            }

        }

        public int IngresoAndroid(Usuario usuario,Dispositivo dispositivo)
        {
            try
            {
                int correcto = 0;
                
               
                if (UsuarioDAO.getInstance().Ingreso(usuario)>=1)
                {
                    if (DispositivoAD.getInstance().Ingreso(dispositivo) == 1) {
                       // AsignacionAD.getInstance()

                    }
                }
                return correcto;

            }
            catch (Exception ex)
            {

                throw ex;
            }
        }
        public int Update(Usuario usuario)
        {
            try
            {
                return UsuarioDAO.getInstance().Update(usuario);
            }
            catch (Exception)
            {

                throw;
            }
        }


        }
}
