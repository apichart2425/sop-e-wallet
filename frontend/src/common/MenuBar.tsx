import { Icon, Layout, Menu, Avatar } from 'antd'
import React, { Fragment, useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import useReactRouter from 'use-react-router'

import Panel from './Panel'

import logo from '../logo.jpg'

import { SelectParam } from 'antd/lib/menu'
import SubMenu from 'antd/lib/menu/SubMenu'
import { useObservable, observer } from 'mobx-react-lite'
import isWindows from '../utils/isWindows'
import { Padding } from '../utils/styled-helper'
import UserStore from '../stores/user'

const { Sider } = Layout

const SideSlider = styled(Sider)`
  overflow: auto;
  height: 100vh;
  position: fixed;
  left: 0;
`
const Center = styled.div`
  width: 100%;
  text-align: center;
  margin: 1.5em 0;
`;

interface ContentLayoutProps {
  collapsed: boolean
}

const ContentLayout = styled.div<ContentLayoutProps>`
  position: absolute;
  width: ${(props: ContentLayoutProps) =>
    props.collapsed
      ? `calc(100vw - ${isWindows() ? '97px' : '80px'})`
      : `calc(100vw - ${isWindows() ? '217px' : '200px'})`};
  left: ${(props: ContentLayoutProps) => (props.collapsed ? '80px' : '200px')};
  padding: 20px;
  transition: all 0.2s;
`

const Footer = styled.div`
  color: #777;
  font-size: 14px;
  width: 100%;
  margin: 30px 0;
  text-align: center;
`

interface MenuItem {
  icon: string
  name: string
  to: string
}

interface MenuItems {
  icon: string
  name: string
  to: string
  submenu?: MenuItem[]
}

interface MenuBarProps {
  children: React.ReactChildren | any
  menus: MenuItems[]
  header?: string
}

const MenuBar = (props: MenuBarProps) => {

  const userStore = useObservable(UserStore)

  const { menus, children } = props

  const [collapsed, setCollapsed] = useState(false)
  const [selected, setSelected] = useState(['1'])

  const { location } = useReactRouter()

  const handleChange = (param: SelectParam) => {
    setSelected([param.key])
  }

  useEffect(() => {
    menus.forEach((menu, i) => {
      if (menu.submenu) {
        menu.submenu.forEach((submenu, j) => {
          if (submenu.to === location.pathname) {
            setSelected([`${i + 1}${j + 1}`])
          }
        })
      }
      if (menu.to === location.pathname) {
        setSelected([`${i + 1}`])
      }
    })
  }, [menus, location.pathname])

  return (
    <Fragment>
      <Layout>
        <SideSlider
          collapsible={true}
          collapsed={collapsed}
          onCollapse={setCollapsed}
        >
          <Center>
            <Avatar src={logo} size={125}></Avatar>
          </Center>

          <Menu
            theme="dark"
            mode="inline"
            selectedKeys={selected}
            onSelect={handleChange}
          >
            {menus.map((menu, i) => {
              if (menu.submenu) {
                const subMenus = menu.submenu.map((submenu, j) => {
                  return (
                    <Menu.Item key={`${i + 1}${j + 1}`}>
                      <Link to={submenu.to}>
                        {submenu.icon && <Icon type={submenu.icon} />}
                        <span className="nav-text">{submenu.name}</span>
                      </Link>
                    </Menu.Item>
                  )
                })
                return (
                  <SubMenu
                    key={i + 1}
                    title={
                      <>
                        <Icon type={menu.icon} />
                        <span className="nav-text">{menu.name}</span>
                      </>
                    }
                  >
                    {subMenus}
                  </SubMenu>
                )
              }

              return (
                <Menu.Item key={i + 1}>
                  <Link to={menu.to}>
                    <Icon type={menu.icon} />
                    <span className="nav-text">{menu.name}</span>
                  </Link>
                </Menu.Item>
              )
            })}
            <Menu.Item onClick={() => userStore.doLogout()}>
              <Icon type={'logout'} />
              <span className="nav-text">Logout</span>
            </Menu.Item>
          </Menu>
        </SideSlider>

        <ContentLayout collapsed={collapsed}>
          <Padding>
            <Panel>{children}</Panel>
          </Padding>

          <Footer>Pachara E-Wallet @2019</Footer>
        </ContentLayout>
      </Layout>
    </Fragment>
  )
}

export default observer(MenuBar)