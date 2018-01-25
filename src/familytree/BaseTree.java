/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.io.File;

/**
 *
 * @author Vinod
 */
abstract class BaseTree {
   protected int maxDepth, nodesCount;
   protected String name;
   protected  File tempStorage;
   
   protected abstract String loadTree(String fileName);
   protected abstract void saveTree();
       

}
