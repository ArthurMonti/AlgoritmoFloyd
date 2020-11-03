package com.mycompany.algoritmofloyd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class InterfaceController implements Initializable {

    @FXML
    private TextArea txtArea;
    @FXML
    private TextArea txtMD;
    @FXML
    private TextArea txtMS;
    @FXML
    private ComboBox<String> cbOrigem;
    @FXML
    private ComboBox<String> cbDestino;

    @FXML
    private Text txtCusto;
    @FXML
    private Text txtCaminho;
    /**
     * Initializes the controller class.
     */
    
    private int MDinicial[][];
    private int MS[][];
    @FXML
    private Button btnCaminho;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtArea.setPromptText("Exemplo: \r"
                + "0 10 50 65 0\r"//0-A
                + "0 0 30 0 4\r"//1-B
                + "0 0 0 20 44\r"//2-C
                + "0 70 0 0 23\r"//3-D
                + "6 0 0 0 0\r"//4-E
                + "\r"//5-F
                + "\r"//6-G
                + "\r"//7-H
                + "\r"//8-I
        );
        
        
        
        
    }

    @FXML
    private void Floyd(ActionEvent event) {
        String linhas[] = txtArea.getText().split("\n");
        
        int tam = linhas.length;
        preencheMatriz(tam,linhas);

        for(int l=0;l<tam;l++)
        {
            for(int i=0;i<tam;i++)
            {
                for(int j=0;j<tam;j++)
                {
                   if(i!=j && i!=l && j!=l && MDinicial[i][l]!=0 && MDinicial[l][j]!=0)
                   {
                       if(MDinicial[i][j]==0)
                       {
                           MDinicial[i][j]=MDinicial[i][l]+MDinicial[l][j];
                           MS[i][j]=l+1;
                       }
                       else if(MDinicial[i][j]>MDinicial[i][l]+MDinicial[l][j]) 
                       {
                           MDinicial[i][j]=MDinicial[i][l]+MDinicial[l][j];
                           MS[i][j]=l+1;
                       }
                   }
                }
            }
        }
        txtMD.clear();
        txtMS.clear();
        
        for(int i=0;i<tam;i++)
        {
            for(int j=0;j<tam;j++)
            {
                txtMD.appendText(String.valueOf(MDinicial[i][j])+" ");
                txtMS.appendText(String.valueOf(MS[i][j])+" ");
            }
            txtMD.appendText("\n");
            txtMS.appendText("\n");
        }
        cbOrigem.setDisable(false);
        cbDestino.setDisable(false);
        btnCaminho.setDisable(false);
    }

    
    
    private void preencheMatriz(int tam,String linhas[])
    {
        MDinicial = new int[tam][tam];
        MS= new int[tam][tam];
        
        for(int i=0;i<tam;i++)
        {
            String numeros[] = linhas[i].split(" ");
            for(int j=0;j<tam;j++)
            {
                MS[i][j]=0;
                MDinicial[i][j]=Integer.parseInt(numeros[j]);
            }  
        }
        List<String> vertices = new ArrayList<>();
        for(int i=0;i<tam;i++)
        {
            vertices.add(Character.toString ((char) 65+i));
            
        }
        
        cbOrigem.setItems(FXCollections.observableArrayList(vertices));
        cbDestino.setItems(FXCollections.observableArrayList(vertices));
    }

    @FXML
    private void Caminho(ActionEvent event) {
        int i =cbOrigem.getSelectionModel().getSelectedIndex();
        int j =cbDestino.getSelectionModel().getSelectedIndex();
        int c;
        if(i>-1 && j>-1)
        {
            txtCusto.setText("Custo: "+MDinicial[i][j]);
            String caminho = ""+Character.toString ((char) 65+j);
            c=j;
            while(c!=0)
            {
               if (MS[i][c] == 0)
                {
                    caminho=Character.toString ((char) 65+i) + " -> "+caminho;
                    c = 0;
                }
                else
                {
                    c = MS[i][c]-1;
                    caminho = Character.toString ((char) 65+c) + " -> "+caminho;
                }    
            }
            txtCaminho.setText("Caminho: "+caminho);
            
        }
        
    }
    
    

}
