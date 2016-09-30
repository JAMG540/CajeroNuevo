package cajero_automatico_molina_garcia_jose_angel_5im8;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class LogicaCajero extends JFrame
{
    JTextField pantalla;
    
    JPanel acciones;
    JPanel eventos;
    
    
    JLabel instrucciones= new JLabel ("Ingresa La cantidad a retirar/depositar y presiona finalizar");
            
    double contador=0;
    String nombre="";
    String validar="0";
    
    
    public LogicaCajero()
    {

//*********** Configuración de la pantalla****************
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null); 
        
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

//Configuración de la pantallita
        
        pantalla = new JTextField("",20);
        pantalla.setBorder(new EmptyBorder(4, 4, 4, 4)); //P
        pantalla.setFont(new Font("Arial", Font.BOLD, 25)); //P
        pantalla.setHorizontalAlignment(JTextField.RIGHT); //P
        pantalla.setEditable(false);
        pantalla.setBackground(Color.WHITE);
        pantalla.setVisible(true);
        panel.add("North", pantalla);
        
 //*******************************Panel de acciones***************************       
        acciones = new JPanel();
        acciones.setLayout(new GridLayout(3,2)); 
        acciones.setBorder(new EmptyBorder(4, 4, 4, 4)); //P
        
        BotonAccion("Crear cuenta");
	BotonAccion("Ver saldo");
        BotonAccion("depositar");
        BotonAccion("retirar");
        BotonAccion("Finalizar");
        BotonAccion("Salir");
        
        panel.add("East", acciones);

 //Conguración panel de eventos
        
        eventos = new JPanel();
        eventos.setLayout(new GridLayout(3,2)); 
        eventos.setBorder(new EmptyBorder(4, 4, 4, 4)); //P

        instrucciones.setVisible(false);
        
        eventos.add(instrucciones);
        eventos.setVisible(true);
        panel.add("Center", eventos);
        
}
    
    public void VisibilidadNo()
    {
        instrucciones.setVisible(false);
        instrucciones.setText("Ingresa La cantidad a retirar/depositar y presiona finalizar");                  
        pantalla.setEditable(false);
        pantalla.setText("");    
    }
    
    private void BotonAccion (String operacion) 
    {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.BLUE);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		acciones.add(btn);
    }
    
    private void operacionPulsado(String tecla) 
    {
        
                if(tecla.equals("Ver saldo"))
                {   
                    if(nombre.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                    }
                    else
                    {
                        pantalla.setText(""+contador);
                    }
                }
                else
		if(tecla.equals("Crear cuenta")) 
                {
                    if(nombre.equals(""))
                    {
                        instrucciones.setText("Escribe tu nombre y presiona finalizar");
                        instrucciones.setVisible(true);
                        pantalla.setEditable(true);
                        pantalla.setText("");
                        pantalla.requestFocus();
                        validar="1";
                        contador=0;
                    
                    }else{
                        int pregunta= JOptionPane.showConfirmDialog(null,"¿Seguro que desea salir?");
                  
                        if(pregunta==JOptionPane.YES_OPTION)
                        {
                            contador=0;
                            setTitle("");
                            nombre="";
                            JOptionPane.showMessageDialog(null, "Vuelve pronto");
                            this.VisibilidadNo();
                        }
                    }
                }
                else
                if (tecla.equals("depositar"))
                {
                    if(nombre.equals(""))
                    {
                       JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                    }
                    else
                    {
                        validar="2";
                        instrucciones.setVisible(true);
                        pantalla.setEditable(true);
                        pantalla.setText("");
                        pantalla.requestFocus();
                    }
                }
                else
                if (tecla.equals("retirar"))
                {
                   if(nombre.equals(""))
                    {
                      JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                    }
                    else
                    {
                        validar="3";
                        instrucciones.setVisible(true);
                        pantalla.setEditable(true);
                        pantalla.setText("");
                        pantalla.requestFocus();
                    }
                }
                else
                if (tecla.equals("Salir"))
                {
                    
                  if(nombre.equals(""))
                  {
                     JOptionPane.showMessageDialog(null, "No has iniciado sesión");
                  }else
                  {
                    int pregunta= JOptionPane.showConfirmDialog(null,"¿deseas donar?");

                    if(pregunta==JOptionPane.YES_OPTION)
                    {
                        String donacion = JOptionPane.showInputDialog("Ingresa el monto  a)5 pesos  b)10 pesos  c)100 pesos");
                        
                        if(donacion.contains("."))
                            {
                                
                                JOptionPane.showMessageDialog(null,"Solo introduce valores enteros por favor");
                            }
                        else{
                              try
                               {
                                 int donativo=Integer.parseInt(donacion);
                                    if(donativo==5 || donativo==10 || donativo==100)
                                    {
                                        try
                                        {
                                            
                                            if(contador-donativo<0)
                                            {
                                            JOptionPane.showMessageDialog(null,"Eres pobre y no puedes donar porque tu saldo quedaria en 0");
                                            }
                                            else{
                                                contador=contador-donativo;
                                                JOptionPane.showMessageDialog(null,"Gracias por tu donativo. Tu saldo Actual es"+contador+"  "+"Vuelve pronto");
                                                contador=0;
                                                setTitle("");
                                                nombre="";
                                                this.VisibilidadNo();
                                            }
                                        }catch(Exception e)
                                        {
                                             JOptionPane.showMessageDialog(null, "Sólo introduce números. Por favor");
                                        }
                                    }
                                    else{
                                    
                                        JOptionPane.showMessageDialog(null,"Monto no valido");
                                    }
                               }catch(Exception e)
                               {
                                   JOptionPane.showMessageDialog(null, "Sólo introduce números. Por favor");
                               }
                            }
                        }
                    else
                       if(pregunta==JOptionPane.NO_OPTION)
                            {
                                contador=0;
                                setTitle("");
                                nombre="";
                                JOptionPane.showMessageDialog(null, "Vuelve pronto");
                                this.VisibilidadNo();
                            }
                  }
                }
                if (tecla.equals("Finalizar"))
                {
                    if(validar.equals("0"))
                    {
                        JOptionPane.showMessageDialog(null,"No has ejecutado ninguna instrucción");
                    }
                    else
                    if(validar.equals("1"))
                    {
                             nombre=pantalla.getText();
                            JOptionPane.showMessageDialog(null,"Bienvenido" +"  "+nombre);
                            setTitle("Cuenta de"+"  "+nombre);
                    }
                    else
                    if(validar.equals("2"))
                    {
                        
                       try
                       {
                           if(pantalla.getText().contains("."))
                            {
                                JOptionPane.showMessageDialog(null,"Sólo valores enteros");
                            }
                           else{
                            contador=contador+Double.parseDouble(pantalla.getText());
                            pantalla.setText(""+contador);
                            JOptionPane.showMessageDialog(null,"Operacion finalizada");
                           }
                       }catch(Exception e)
                       {
                           JOptionPane.showMessageDialog(null,"Sólo valores numéricos");
                       }
                        
                    }
                    else
                    if(validar.equals("3"))
                    {
                        try{
                            
                            if (contador-Double.parseDouble(pantalla.getText())<0)
                            {
                                JOptionPane.showMessageDialog(null,"La cantidad de retiro es mayor al saldo solo cuentas con:"+contador+" "+"pesos");
                            }
                            else
                            {
                                if(pantalla.getText().contains("."))
                                {
                                    JOptionPane.showMessageDialog(null,"Sólo valores enteros");
                                }
                                else
                                {
                                    contador=contador-Double.parseDouble(pantalla.getText());
                                    pantalla.setText(""+contador);
                                    JOptionPane.showMessageDialog(null,"Operacion finalizada");
                                }
                            }
                        }
                        catch(Exception e)
                        {
                           JOptionPane.showMessageDialog(null,"Sólo valores numéricos");
                        }

                    }
                    this.VisibilidadNo();
                
                }
      }
}
