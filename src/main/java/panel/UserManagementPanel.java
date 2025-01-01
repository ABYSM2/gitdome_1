package panel;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementPanel extends JPanel {
    DefaultTableModel tableModel;
    JTable bookTable;

    public UserManagementPanel() {
        setLayout(new BorderLayout()); // 使用 BorderLayout 让组件填充整个面板
        setBackground(Color.LIGHT_GRAY);

        // 创建图书表格
        String[] columns = {"用户名", "角色", "账号状态", "允许登录"};//列名字段值——字符串数组
        tableModel = new DefaultTableModel(columns, 0);//表格模型
        bookTable = new JTable(tableModel){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };//放入表格模型
        JScrollPane userScrollPane = new JScrollPane(bookTable);//滚动轴


        // 设置滚动面板在面板中占据中心位置
        add(userScrollPane, BorderLayout.CENTER);

        // 加载用户数据
        lodeUsers();
    }

    private void lodeUsers() {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();
        tableModel.setRowCount(0);  // 清空表格内容

        for (User user : users) {
            String username = user.getUsername();
            String role;
                  if  (user.getRole().equals("A")) {
                      role = "管理员";
                  }else  {
                      role = "读者";
                  }
            String state;
                  if(user.getState().equals("A")) {
                      state="活跃";
                  }else{
                      state="注销";
                  }
            String situation;
                  if(user.getSituation().equals("A")) {
                      situation = "正常";
                  }else{
                      situation = "拉黑";
                  }

            // 添加到表格中
            Object[] row = {
                    username,
                    role,
                    state,
                    situation
            };

            tableModel.addRow(row);
        }
    }
}
