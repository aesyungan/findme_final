using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CapaEntidades;
using System.Data.SqlClient;
using System.Data;

namespace CapaAccesoAtos
{
    public class UsuarioDAO
    {

        #region "PATRON SINGLETON"
        private static UsuarioDAO usuarioDAO = null;
        private UsuarioDAO()
        {

        }
        public static UsuarioDAO getInstance()
        {
            if (usuarioDAO == null)
            {
                usuarioDAO = new UsuarioDAO();
            }
            return usuarioDAO;
        }
        #endregion

        public Usuario VerificarUsuario(Usuario usuario)
        {
            Usuario usuarioreturn = null;
            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;

            try
            {
                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_veriicarUsuario, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.AddWithValue("@user", usuario.usuario);
                cmd.Parameters.AddWithValue("@pass", usuario.contrasena);
                //abrimos Coneccion
                coneccion.Open();
                reader = cmd.ExecuteReader();
                if (reader.Read())//si existe datos
                {
                    usuarioreturn = new Usuario();
                    usuarioreturn.id_usuario = Convert.ToInt32(reader[0].ToString());
                    usuarioreturn.rol.id_rol = Convert.ToInt32(reader[1].ToString());
                    usuarioreturn.nombres = reader["nombres"].ToString();
                    usuarioreturn.apellidos = reader["apellidos"].ToString();
                    usuarioreturn.cedula = reader["cedula"].ToString();
                    usuarioreturn.foto = reader["foto"].ToString();
                    usuarioreturn.usuario = reader["usuario"].ToString();
                    usuarioreturn.contrasena = reader["contrasena"].ToString();
                    usuarioreturn.estado = Convert.ToInt32(reader["estado"].ToString());
                    usuarioreturn.edad = Convert.ToInt32(reader["edad"].ToString());
                    usuarioreturn.direccion = reader["direccion"].ToString();


                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return usuarioreturn;
        }


        //ingreso 


        public int Ingreso(Usuario usuario)
        {
            Usuario usuarioreturn = null;
            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;
            List<Usuario> lstUsuario = new List<Usuario>();
            lstUsuario = Listar();
            Boolean registrado = false;
            int correcto = 0;
            foreach (Usuario item in lstUsuario)
            {
                if (item.usuario == usuario.usuario)
                {
                    registrado = true;
                }
            }

            if (!registrado)
            {
                try
                {

                    //optenemos coneccion
                    coneccion = Conexion.getInstance().ConeccionDB();
                    //nombre Procedimiento
                    cmd = new SqlCommand(SettingsApp.Default.pro_usuario_insertar, coneccion);
                    //especificamos el tipo de procediemto ene ste caso es un procedimento
                    cmd.CommandType = CommandType.StoredProcedure;

                    cmd.Parameters.Add("@id_rol", usuario.rol.id_rol);
                    cmd.Parameters.Add("@nombres", usuario.nombres);
                    cmd.Parameters.Add("@apellidos", usuario.apellidos);
                    cmd.Parameters.Add("@cedula", usuario.cedula);
                    cmd.Parameters.Add("@foto", usuario.foto);
                    cmd.Parameters.Add("@usuario", usuario.usuario);
                    cmd.Parameters.Add("@contrasena", usuario.contrasena);
                    cmd.Parameters.Add("@estado", usuario.estado);
                    cmd.Parameters.Add("@edad", usuario.edad);
                    cmd.Parameters.Add("@direccion", usuario.direccion);

                    //abrimos Coneccion
                    coneccion.Open();
                    //sejecuta y retorna las filas afectadas
                    correcto = cmd.ExecuteNonQuery();

                }
                catch (Exception ex)
                {

                    throw ex;
                }
                finally
                {
                    coneccion.Close();
                }

            }

            return correcto;
        }



        //listar


        public List<Usuario> Listar()
        {
            List<Usuario> listUsuarioreturn = new List<Usuario>();

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;

            try
            {
                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.pro_listar_Usuario, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                //abrimos Coneccion
                coneccion.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())//si existe datos
                {
                    Usuario usuarioreturn = new Usuario();
                    usuarioreturn = new Usuario();
                    usuarioreturn.id_usuario = Convert.ToInt32(reader[0].ToString());
                    usuarioreturn.rol.id_rol = Convert.ToInt32(reader[1].ToString());
                    usuarioreturn.rol.descripcion_rol = reader["descripcion_rol"].ToString();
                    usuarioreturn.nombres = reader["nombres"].ToString();
                    usuarioreturn.apellidos = reader["apellidos"].ToString();
                    usuarioreturn.cedula = reader["cedula"].ToString();
                    usuarioreturn.foto = reader["foto"].ToString();
                    usuarioreturn.usuario = reader["usuario"].ToString();
                    usuarioreturn.contrasena = reader["contrasena"].ToString();
                    usuarioreturn.estado = Convert.ToInt32(reader["estado"].ToString());
                    usuarioreturn.edad = Convert.ToInt32(reader["edad"].ToString());
                    usuarioreturn.direccion = reader["direccion"].ToString();
                    listUsuarioreturn.Add(usuarioreturn);
                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return listUsuarioreturn;
        }

        //eliminar


        public int Eliminar(Usuario usuario)
        {

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.pro_eliminar_usuario, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@id_usuario", usuario.id_usuario);


                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }


        //update
        public int Update(Usuario usuario)
        {
            int correcto = 0;
            SqlCommand cmd = null;
            SqlConnection coneccion = null;
            try
            {


                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.pro_usuario_update, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@id_usuario", usuario.id_usuario);
                cmd.Parameters.Add("@id_rol", usuario.rol.id_rol);
                cmd.Parameters.Add("@nombres", usuario.nombres);
                cmd.Parameters.Add("@apellidos", usuario.apellidos);
                cmd.Parameters.Add("@cedula", usuario.cedula);
                cmd.Parameters.Add("@foto", usuario.foto);
                cmd.Parameters.Add("@usuario", usuario.usuario);
                cmd.Parameters.Add("@contrasena", usuario.contrasena);
                cmd.Parameters.Add("@estado", usuario.estado);
                cmd.Parameters.Add("@edad", usuario.edad);
                cmd.Parameters.Add("@direccion", usuario.direccion);

                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }



            return correcto;
        }
    }
}
